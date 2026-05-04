package com.anthropic.core.auth

import java.time.Duration
import java.time.Instant
import java.util.concurrent.CompletableFuture
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * A thread-safe, deduplicating cache in front of an [AccessTokenProvider].
 *
 * Returns the cached token while it's fresh, and coordinates refreshes when it isn't so concurrent
 * callers share a single upstream request.
 *
 * The sync path ([get]) blocks the first caller on refresh; others wait. The async path
 * ([getAsync]) refreshes in the background when the token is near expiry but still usable, and only
 * forces callers to wait once it crosses [mandatoryRefreshThreshold]. `forceRefresh` bypasses the
 * cache on both paths.
 */
internal class CachingAccessTokenProvider(
    private val provider: AccessTokenProvider,
    private val mandatoryRefreshThreshold: Duration = Duration.ofSeconds(30),
    private val advisoryRefreshThreshold: Duration = Duration.ofMinutes(5),
    private val advisoryRefreshBackoff: Duration = Duration.ofSeconds(5),
    private val clock: () -> Instant = Instant::now,
) : AccessTokenProvider {

    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    // All fields below guarded by `lock`.
    private var cached: AccessToken? = null
    private var cachedBaseUrl: String? = null

    // Sync refresh state: a simple flag + condition. No future needed — waiters
    // just re-check the cache when signaled.
    private var syncRefreshing: Boolean = false
    private var syncRefreshForce: Boolean = false

    // Async refresh state: a future other async callers can join, plus whether
    // it's a force refresh (so force callers don't accept non-force results).
    private var asyncInFlight: CompletableFuture<AccessToken>? = null
    private var asyncInFlightForce: Boolean = false

    private var lastAdvisoryError: Instant = Instant.EPOCH

    private enum class Freshness {
        /** Fully usable, no refresh needed. */
        FRESH,
        /** Still usable but past advisory threshold — background refresh desirable. */
        ADVISORY,
        /** Must refresh before use (past mandatory threshold, expired, or absent). */
        MANDATORY,
    }

    override fun get(baseUrl: String, forceRefresh: Boolean): AccessToken {
        lock.withLock {
            while (true) {
                // Wait out any in-flight sync refresh that we can't bypass.
                // A force caller must not accept a non-force refresh's result.
                while (syncRefreshing && !forceRefresh) {
                    // Non-force caller: wait for the in-flight refresh; its
                    // result may satisfy us.
                    condition.await()
                }
                while (syncRefreshing && forceRefresh && !syncRefreshForce) {
                    // Force caller waiting out a non-force refresh.
                    condition.await()
                }

                if (!forceRefresh) {
                    val token = usableCachedUnsafe(baseUrl)
                    if (token != null) return token
                }

                // No in-flight refresh and we need one — claim the slot.
                if (!syncRefreshing) {
                    syncRefreshing = true
                    syncRefreshForce = forceRefresh
                    break
                }
                // Otherwise loop and wait again.
                condition.await()
            }
        }

        try {
            val token = provider.get(baseUrl, forceRefresh)
            lock.withLock { storeUnsafe(token, baseUrl) }
            return token
        } finally {
            lock.withLock {
                syncRefreshing = false
                syncRefreshForce = false
                condition.signalAll()
            }
        }
    }

    private sealed class AsyncAction {
        data class ReturnCached(val token: AccessToken) : AsyncAction()

        data class Join(val future: CompletableFuture<AccessToken>) : AsyncAction()

        data class Background(
            val future: CompletableFuture<AccessToken>,
            val fallback: AccessToken,
        ) : AsyncAction()

        data class Foreground(val future: CompletableFuture<AccessToken>, val force: Boolean) :
            AsyncAction()
    }

    override fun getAsync(baseUrl: String, forceRefresh: Boolean): CompletableFuture<AccessToken> {
        val action = lock.withLock { planAsyncUnsafe(baseUrl, forceRefresh) }
        return when (action) {
            is AsyncAction.ReturnCached -> CompletableFuture.completedFuture(action.token)
            is AsyncAction.Join -> action.future
            is AsyncAction.Background -> {
                launchAsyncRefresh(baseUrl, action.future, force = false, advisory = true)
                CompletableFuture.completedFuture(action.fallback)
            }
            is AsyncAction.Foreground -> {
                launchAsyncRefresh(baseUrl, action.future, force = action.force, advisory = false)
                action.future
            }
        }
    }

    private fun planAsyncUnsafe(baseUrl: String, forceRefresh: Boolean): AsyncAction {
        // Force callers: can only join another force refresh; otherwise start own.
        if (forceRefresh) {
            asyncInFlight?.let { existing ->
                if (asyncInFlightForce) return AsyncAction.Join(existing)
                // Non-force refresh in flight — we can't trust its result.
                // chainAfter installs the new in-flight slot AND schedules the
                // upstream call once `existing` finishes, so we Join here rather
                // than Foreground (which would launch a second upstream call).
                return AsyncAction.Join(chainAfter(existing, baseUrl))
            }
            val future = CompletableFuture<AccessToken>()
            asyncInFlight = future
            asyncInFlightForce = true
            return AsyncAction.Foreground(future, force = true)
        }

        // Non-force: consult cache.
        val token = cached.takeIf { cachedBaseUrl == baseUrl }
        val freshness = classify(token)

        if (freshness == Freshness.FRESH && token != null) {
            return AsyncAction.ReturnCached(token)
        }

        // Advisory: we have a usable token. Return it immediately and, if no
        // refresh is in flight and we're outside the backoff window, kick one off
        // in the background. Critically, we do NOT join an in-flight refresh here
        // — if it fails, we'd surface that failure to a caller who had a perfectly
        // good cached token.
        if (freshness == Freshness.ADVISORY && token != null) {
            if (asyncInFlight == null && canAttemptAdvisoryUnsafe()) {
                val future = CompletableFuture<AccessToken>()
                asyncInFlight = future
                asyncInFlightForce = false
                return AsyncAction.Background(future, token)
            }
            return AsyncAction.ReturnCached(token)
        }

        // Mandatory or no cache: caller must wait. Join any in-flight refresh.
        asyncInFlight?.let {
            return AsyncAction.Join(it)
        }

        val future = CompletableFuture<AccessToken>()
        asyncInFlight = future
        asyncInFlightForce = false
        return AsyncAction.Foreground(future, force = false)
    }

    /**
     * Returns a future that waits for `prior` to finish (success or failure) and then becomes the
     * new in-flight slot for our own refresh.
     */
    private fun chainAfter(
        prior: CompletableFuture<AccessToken>,
        baseUrl: String,
    ): CompletableFuture<AccessToken> {
        val future = CompletableFuture<AccessToken>()
        // We set ourselves as the in-flight slot now so other force callers
        // arriving during `prior` will join us.
        asyncInFlight = future
        asyncInFlightForce = true
        prior.whenComplete { _, _ ->
            provider.getAsync(baseUrl, true).whenComplete { token, err ->
                completeAsync(future, baseUrl, token, err, advisory = false)
            }
        }
        return future
    }

    private fun launchAsyncRefresh(
        baseUrl: String,
        future: CompletableFuture<AccessToken>,
        force: Boolean,
        advisory: Boolean,
    ) {
        provider.getAsync(baseUrl, force).whenComplete { token, err ->
            completeAsync(future, baseUrl, token, err, advisory)
        }
    }

    private fun completeAsync(
        future: CompletableFuture<AccessToken>,
        baseUrl: String,
        token: AccessToken?,
        err: Throwable?,
        advisory: Boolean,
    ) {
        lock.withLock {
            if (err == null && token != null) {
                storeUnsafe(token, baseUrl)
            } else if (advisory) {
                lastAdvisoryError = clock()
            }
            if (asyncInFlight === future) {
                asyncInFlight = null
                asyncInFlightForce = false
            }
        }
        // Complete outside the lock.
        if (err != null) future.completeExceptionally(err) else future.complete(token)
    }

    fun invalidate(): Boolean =
        lock.withLock {
            if (cached == null) return@withLock false
            cached = null
            cachedBaseUrl = null
            true
        }

    private fun storeUnsafe(token: AccessToken, baseUrl: String) {
        cached = token
        cachedBaseUrl = baseUrl
        lastAdvisoryError = Instant.EPOCH
    }

    private fun usableCachedUnsafe(baseUrl: String): AccessToken? {
        val token = cached ?: return null
        if (cachedBaseUrl != baseUrl) {
            cached = null
            cachedBaseUrl = null
            return null
        }
        return if (classify(token) != Freshness.MANDATORY) token else null
    }

    private fun classify(token: AccessToken?): Freshness {
        if (token == null) return Freshness.MANDATORY
        val expiresAt = token.expiresAt ?: return Freshness.FRESH
        val remaining = Duration.between(clock(), expiresAt)
        return when {
            remaining <= mandatoryRefreshThreshold -> Freshness.MANDATORY
            remaining <= advisoryRefreshThreshold -> Freshness.ADVISORY
            else -> Freshness.FRESH
        }
    }

    private fun canAttemptAdvisoryUnsafe(): Boolean {
        val since = Duration.between(lastAdvisoryError, clock())
        return since >= advisoryRefreshBackoff
    }
}
