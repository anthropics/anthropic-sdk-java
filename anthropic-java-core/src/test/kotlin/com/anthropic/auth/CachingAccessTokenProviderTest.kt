package com.anthropic.auth

import com.anthropic.core.auth.AccessToken
import com.anthropic.core.auth.AccessTokenProvider
import com.anthropic.core.auth.CachingAccessTokenProvider
import java.time.Duration
import java.time.Instant
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class CachingAccessTokenProviderTest {

    private val baseUrl = "https://api.anthropic.com"

    private fun provider(
        tokenFn: (baseUrl: String, forceRefresh: Boolean) -> AccessToken
    ): AccessTokenProvider =
        object : AccessTokenProvider {
            override fun get(baseUrl: String, forceRefresh: Boolean) =
                tokenFn(baseUrl, forceRefresh)

            override fun getAsync(baseUrl: String, forceRefresh: Boolean) =
                CompletableFuture.supplyAsync { tokenFn(baseUrl, forceRefresh) }
        }

    private fun asyncProvider(
        tokenFn: (baseUrl: String, forceRefresh: Boolean) -> AccessToken
    ): AccessTokenProvider {
        val executor = Executors.newCachedThreadPool()
        return object : AccessTokenProvider {
            override fun get(baseUrl: String, forceRefresh: Boolean) =
                tokenFn(baseUrl, forceRefresh)

            override fun getAsync(baseUrl: String, forceRefresh: Boolean) =
                CompletableFuture.supplyAsync({ tokenFn(baseUrl, forceRefresh) }, executor)
        }
    }

    @Test
    fun firstCallFetches() {
        val calls = AtomicInteger(0)
        val cache =
            CachingAccessTokenProvider(
                provider { _, _ ->
                    calls.incrementAndGet()
                    AccessToken("tok-1", Instant.now().plusSeconds(3600))
                }
            )

        val token = cache.get(baseUrl, false)

        assertThat(token.token).isEqualTo("tok-1")
        assertThat(calls.get()).isEqualTo(1)
    }

    @Test
    fun firstCallFetchesAsync() {
        val calls = AtomicInteger(0)
        val cache =
            CachingAccessTokenProvider(
                provider { _, _ ->
                    calls.incrementAndGet()
                    AccessToken("tok-1", Instant.now().plusSeconds(3600))
                }
            )

        val token = cache.getAsync(baseUrl, false).get()

        assertThat(token.token).isEqualTo("tok-1")
        assertThat(calls.get()).isEqualTo(1)
    }

    @Test
    fun noExpiryNeverRefreshes() {
        val calls = AtomicInteger(0)
        val cache =
            CachingAccessTokenProvider(
                provider { _, _ ->
                    calls.incrementAndGet()
                    AccessToken("tok-forever", expiresAt = null)
                }
            )

        repeat(5) {
            val token = cache.get(baseUrl, false)
            assertThat(token.token).isEqualTo("tok-forever")
        }
        assertThat(calls.get()).isEqualTo(1)
    }

    @Test
    fun freshTokenNotRefetched() {
        val calls = AtomicInteger(0)
        val cache =
            CachingAccessTokenProvider(
                provider { _, _ ->
                    calls.incrementAndGet()
                    AccessToken("fresh", Instant.now().plusSeconds(600))
                }
            )

        repeat(3) { assertThat(cache.get(baseUrl, false).token).isEqualTo("fresh") }
        assertThat(calls.get()).isEqualTo(1)
    }

    // --- Advisory refresh ---------------------------------------------------

    @Test
    fun advisoryRefreshSuccessAsync() {
        // Async path: advisory-zone token should be returned immediately, and a
        // background refresh should eventually populate the cache with a fresh one.
        val calls = AtomicInteger(0)
        val cache =
            CachingAccessTokenProvider(
                asyncProvider { _, _ ->
                    val n = calls.incrementAndGet()
                    if (n == 1) AccessToken("stale", Instant.now().plusSeconds(60)) // advisory zone
                    else AccessToken("refreshed", Instant.now().plusSeconds(600))
                }
            )

        // Prime the cache.
        assertThat(cache.getAsync(baseUrl, false).get().token).isEqualTo("stale")

        // Second call returns stale immediately and triggers background refresh.
        assertThat(cache.getAsync(baseUrl, false).get().token).isEqualTo("stale")

        // Poll until the background refresh lands.
        val deadline = System.nanoTime() + Duration.ofSeconds(2).toNanos()
        var got: String = ""
        while (System.nanoTime() < deadline) {
            got = cache.getAsync(baseUrl, false).get().token
            if (got == "refreshed") return
            Thread.sleep(1)
        }
        throw AssertionError("background refresh did not complete in time; last token=$got")
    }

    @Test
    fun advisoryRefreshFailureServesStale() {
        // If the background refresh fails, the cached (advisory-zone) token is
        // still served, and the backoff suppresses another attempt.
        val calls = AtomicInteger(0)
        val refreshAttempted = CountDownLatch(1)
        val cache =
            CachingAccessTokenProvider(
                asyncProvider { _, _ ->
                    val n = calls.incrementAndGet()
                    if (n == 1) {
                        AccessToken("stale", Instant.now().plusSeconds(60))
                    } else {
                        refreshAttempted.countDown()
                        throw RuntimeException("refresh failed")
                    }
                }
            )

        // Prime.
        cache.getAsync(baseUrl, false).get()

        // Triggers background refresh; returns stale.
        assertThat(cache.getAsync(baseUrl, false).get().token).isEqualTo("stale")

        // Wait for the failing refresh to actually run.
        assertThat(refreshAttempted.await(2, TimeUnit.SECONDS)).isTrue()

        // Subsequent calls within the backoff window still serve stale and must
        // not trigger further refresh attempts.
        val callsAfterFailure = calls.get()
        repeat(3) { assertThat(cache.getAsync(baseUrl, false).get().token).isEqualTo("stale") }
        assertThat(calls.get()).isEqualTo(callsAfterFailure)
    }

    // --- Mandatory refresh --------------------------------------------------

    @Test
    fun mandatoryRefreshFailurePropagates() {
        val calls = AtomicInteger(0)
        val cache =
            CachingAccessTokenProvider(
                provider { _, _ ->
                    val n = calls.incrementAndGet()
                    if (n == 1)
                        AccessToken("expiring", Instant.now().plusSeconds(10)) // mandatory zone
                    else throw RuntimeException("refresh failed")
                }
            )

        // Prime with near-expiry token.
        cache.get(baseUrl, false)

        assertThatThrownBy { cache.get(baseUrl, false) }.hasMessageContaining("refresh failed")
    }

    @Test
    fun mandatoryRefreshFailurePropagatesAsync() {
        val calls = AtomicInteger(0)
        val cache =
            CachingAccessTokenProvider(
                provider { _, _ ->
                    val n = calls.incrementAndGet()
                    if (n == 1) AccessToken("expiring", Instant.now().plusSeconds(10))
                    else throw RuntimeException("refresh failed")
                }
            )

        cache.getAsync(baseUrl, false).get()

        assertThatThrownBy { cache.getAsync(baseUrl, false).get() }
            .isInstanceOf(ExecutionException::class.java)
            .hasCauseInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("refresh failed")
    }

    @Test
    fun expiredIsMandatory() {
        val calls = AtomicInteger(0)
        val cache =
            CachingAccessTokenProvider(
                provider { _, _ ->
                    val n = calls.incrementAndGet()
                    if (n == 1) AccessToken("expired", Instant.now().minusSeconds(1))
                    else AccessToken("new", Instant.now().plusSeconds(600))
                }
            )

        cache.get(baseUrl, false)
        assertThat(cache.get(baseUrl, false).token).isEqualTo("new")
    }

    // --- Force refresh ------------------------------------------------------

    @Test
    fun forceRefreshBypassesCache() {
        val calls = AtomicInteger(0)
        val cache =
            CachingAccessTokenProvider(
                provider { _, _ ->
                    AccessToken("tok-${calls.incrementAndGet()}", Instant.now().plusSeconds(3600))
                }
            )

        assertThat(cache.get(baseUrl, false).token).isEqualTo("tok-1")
        assertThat(cache.get(baseUrl, true).token).isEqualTo("tok-2")
        assertThat(calls.get()).isEqualTo(2)
    }

    @Test
    fun cachesSeparatelyPerBaseUrl() {
        val calls = AtomicInteger(0)
        val cache =
            CachingAccessTokenProvider(
                provider { url, _ ->
                    calls.incrementAndGet()
                    AccessToken("token-for-$url", Instant.now().plusSeconds(3600))
                }
            )

        assertThat(cache.get("https://api.anthropic.com", false).token)
            .isEqualTo("token-for-https://api.anthropic.com")
        assertThat(cache.get("https://api.other.com", false).token)
            .isEqualTo("token-for-https://api.other.com")
        // Switching back refetches: cache holds only one baseUrl at a time.
        assertThat(cache.get("https://api.anthropic.com", false).token)
            .isEqualTo("token-for-https://api.anthropic.com")
        assertThat(calls.get()).isEqualTo(3)
    }

    @Test
    fun invalidateDropsCache() {
        val calls = AtomicInteger(0)
        val cache =
            CachingAccessTokenProvider(
                provider { _, _ ->
                    AccessToken("tok-${calls.incrementAndGet()}", Instant.now().plusSeconds(3600))
                }
            )

        assertThat(cache.get(baseUrl, false).token).isEqualTo("tok-1")
        assertThat(cache.invalidate()).isTrue()
        assertThat(cache.get(baseUrl, false).token).isEqualTo("tok-2")
    }

    @Test
    fun invalidateReturnsFalseWhenEmpty() {
        val cache =
            CachingAccessTokenProvider(
                provider { _, _ -> AccessToken("tok", Instant.now().plusSeconds(3600)) }
            )
        assertThat(cache.invalidate()).isFalse()
    }

    @Test
    fun concurrentSyncCallersDeduplicate() {
        val calls = AtomicInteger(0)
        val release = CountDownLatch(1)
        val allInside = CountDownLatch(10)

        val cache =
            CachingAccessTokenProvider(
                provider { _, _ ->
                    calls.incrementAndGet()
                    // Block the first caller until every goroutine... sorry, every thread
                    // has entered the cache, so they all contend for the same refresh.
                    release.await()
                    AccessToken("concurrent", Instant.now().plusSeconds(3600))
                }
            )

        val threads =
            (1..10).map {
                Thread {
                        allInside.countDown()
                        val token = cache.get(baseUrl, false)
                        assertThat(token.token).isEqualTo("concurrent")
                    }
                    .also { it.start() }
            }

        // Give threads a moment to queue up on the lock, then release.
        assertThat(allInside.await(2, TimeUnit.SECONDS)).isTrue()
        Thread.sleep(50)
        release.countDown()

        threads.forEach { it.join(5_000) }
        assertThat(calls.get()).isEqualTo(1)
    }

    @Test
    fun concurrentAsyncCallersDeduplicate() {
        val calls = AtomicInteger(0)
        val release = CountDownLatch(1)
        val executor = Executors.newCachedThreadPool()

        val p =
            object : AccessTokenProvider {
                override fun get(baseUrl: String, forceRefresh: Boolean): AccessToken {
                    throw UnsupportedOperationException()
                }

                override fun getAsync(baseUrl: String, forceRefresh: Boolean) =
                    CompletableFuture.supplyAsync(
                        {
                            calls.incrementAndGet()
                            release.await()
                            AccessToken("concurrent", Instant.now().plusSeconds(3600))
                        },
                        executor,
                    )
            }
        val cache = CachingAccessTokenProvider(p)

        val futures = (1..10).map { cache.getAsync(baseUrl, false) }
        // All callers have submitted; unblock the single provider call.
        Thread.sleep(50)
        release.countDown()

        futures.forEach { assertThat(it.get(5, TimeUnit.SECONDS).token).isEqualTo("concurrent") }
        assertThat(calls.get()).isEqualTo(1)
    }
}
