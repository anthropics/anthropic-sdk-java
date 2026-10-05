package com.anthropic.auth

import com.anthropic.core.auth.AccessToken
import com.anthropic.core.auth.AccessTokenProvider
import com.anthropic.core.auth.CachingAccessTokenProvider
import java.time.Instant
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class CachingAccessTokenProviderSynchronousFailureTest {
    private val baseUrl = "https://api.anthropic.com"
    private val failure = IllegalStateException("provider failed before returning a future")
    private val fresh = AccessToken("fresh", expiresAt = null)

    private fun provider(call: (Boolean) -> CompletableFuture<AccessToken>): AccessTokenProvider =
        object : AccessTokenProvider {
            override fun get(baseUrl: String, forceRefresh: Boolean): AccessToken =
                throw UnsupportedOperationException("sync path not used")

            override fun getAsync(baseUrl: String, forceRefresh: Boolean) = call(forceRefresh)
        }

    private fun assertFailed(future: CompletableFuture<AccessToken>) {
        assertThatThrownBy { future.get(5, TimeUnit.SECONDS) }
            .isInstanceOf(ExecutionException::class.java)
            .hasCause(failure)
    }

    @Test
    fun foregroundFailureCompletesTheFutureAndAllowsAnotherFetch() {
        var calls = 0
        val cache =
            CachingAccessTokenProvider(
                provider {
                    if (++calls == 1) throw failure
                    CompletableFuture.completedFuture(fresh)
                }
            )
        assertFailed(cache.getAsync(baseUrl, false))
        assertThat(cache.getAsync(baseUrl, false).get(1, TimeUnit.SECONDS)).isSameAs(fresh)
        assertThat(calls).isEqualTo(2)
    }

    @Test
    fun mandatoryFailureDoesNotLeaveLaterCallsWaiting() {
        var calls = 0
        val cache =
            CachingAccessTokenProvider(
                provider {
                    when (++calls) {
                        1 ->
                            CompletableFuture.completedFuture(AccessToken("expired", Instant.EPOCH))
                        2 -> throw failure
                        else -> CompletableFuture.completedFuture(fresh)
                    }
                }
            )
        cache.getAsync(baseUrl, false).get(1, TimeUnit.SECONDS)
        assertFailed(cache.getAsync(baseUrl, false))
        assertThat(cache.getAsync(baseUrl, false).get(1, TimeUnit.SECONDS)).isSameAs(fresh)
        assertThat(calls).isEqualTo(3)
    }

    @Test
    fun advisoryFailureStillServesTheTokenAndUsesBackoff() {
        var now = Instant.parse("2026-01-01T00:00:00Z")
        val stale = AccessToken("stale", now.plusSeconds(60))
        var calls = 0
        val cache =
            CachingAccessTokenProvider(
                provider {
                    when (++calls) {
                        1 -> CompletableFuture.completedFuture(stale)
                        2 -> throw failure
                        else -> CompletableFuture.completedFuture(fresh)
                    }
                },
                clock = { now },
            )
        assertThat(cache.getAsync(baseUrl, false).get(1, TimeUnit.SECONDS)).isSameAs(stale)
        repeat(3) {
            assertThat(cache.getAsync(baseUrl, false).get(1, TimeUnit.SECONDS)).isSameAs(stale)
        }
        assertThat(calls).isEqualTo(2)
        now = now.plusSeconds(6)
        assertThat(cache.getAsync(baseUrl, false).get(1, TimeUnit.SECONDS)).isSameAs(stale)
        assertThat(cache.getAsync(baseUrl, false).get(1, TimeUnit.SECONDS)).isSameAs(fresh)
        assertThat(calls).isEqualTo(3)
    }

    @Test
    fun chainedForceFailureCompletesAllWaitersAndReleasesTheSlot() {
        val pending = CompletableFuture<AccessToken>()
        val forces = mutableListOf<Boolean>()
        val cache =
            CachingAccessTokenProvider(
                provider { force ->
                    forces.add(force)
                    when (forces.size) {
                        1 -> pending
                        2 -> throw failure
                        else -> CompletableFuture.completedFuture(fresh)
                    }
                }
            )
        val first = cache.getAsync(baseUrl, false)
        val forced = cache.getAsync(baseUrl, true)
        val joined = cache.getAsync(baseUrl, true)
        assertThat(joined).isSameAs(forced)
        pending.completeExceptionally(IllegalArgumentException("prior attempt failed"))
        assertThat(first.isCompletedExceptionally).isTrue()
        assertFailed(forced)
        assertFailed(joined)
        assertThat(cache.getAsync(baseUrl, false).get(1, TimeUnit.SECONDS)).isSameAs(fresh)
        assertThat(forces).containsExactly(false, true, false)
    }

    @Test
    fun alreadyExceptionalFutureRetainsExistingBehavior() {
        var calls = 0
        val cache =
            CachingAccessTokenProvider(
                provider {
                    if (++calls == 1)
                        CompletableFuture<AccessToken>().also { it.completeExceptionally(failure) }
                    else CompletableFuture.completedFuture(fresh)
                }
            )
        assertFailed(cache.getAsync(baseUrl, false))
        assertThat(cache.getAsync(baseUrl, false).get(1, TimeUnit.SECONDS)).isSameAs(fresh)
    }
}
