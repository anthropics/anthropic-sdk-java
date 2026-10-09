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

internal class CachingAccessTokenProviderCancellationTest {
    private val url = "https://example.test"

    private class ControlledProvider : AccessTokenProvider {
        val requests = mutableListOf<Pair<Boolean, CompletableFuture<AccessToken>>>()

        override fun get(baseUrl: String, forceRefresh: Boolean): AccessToken =
            throw UnsupportedOperationException("async-only test provider")

        override fun getAsync(
            baseUrl: String,
            forceRefresh: Boolean,
        ): CompletableFuture<AccessToken> {
            val future = CompletableFuture<AccessToken>()
            requests.add(forceRefresh to future)
            return future
        }
    }

    @Test fun cancellingLeaderDoesNotCancelOtherCallers() = verifyCallerCancellation(0)

    @Test fun cancellingJoinedCallerDoesNotCancelOtherCallers() = verifyCallerCancellation(1)

    private fun verifyCallerCancellation(cancelled: Int) {
        val provider = ControlledProvider()
        val cache = CachingAccessTokenProvider(provider)
        val callers = List(3) { cache.getAsync(url, false) }
        assertThat(callers[cancelled].cancel(true)).isTrue()
        val survivor = callers[(cancelled + 1) % callers.size]
        assertThat(survivor.isDone).isFalse()
        val late = cache.getAsync(url, false)
        assertThat(late.isDone).isFalse()
        assertThat(provider.requests).hasSize(1)
        val token = AccessToken("genuine", null)
        provider.requests.single().second.complete(token)
        assertThat(survivor.get(2, TimeUnit.SECONDS)).isSameAs(token)
        assertThat(late.get(2, TimeUnit.SECONDS)).isSameAs(token)
        assertThat(cache.getAsync(url, false).get(2, TimeUnit.SECONDS)).isSameAs(token)
        assertThat(callers[cancelled].isCancelled).isTrue()
        assertThat(provider.requests).hasSize(1)
    }

    @Test
    fun cancellationDoesNotLaunchAForcedRefreshBeforeTheOriginalFinishes() {
        val provider = ControlledProvider()
        val cache = CachingAccessTokenProvider(provider)
        val original = cache.getAsync(url, false)
        val forced = cache.getAsync(url, true)
        val joined = cache.getAsync(url, true)
        assertThat(original.cancel(false)).isTrue()
        assertThat(provider.requests).hasSize(1)
        assertThat(forced.cancel(false)).isTrue()
        assertThat(joined.isDone).isFalse()
        provider.requests[0].second.complete(AccessToken("old", null))
        assertThat(provider.requests.map { it.first }).containsExactly(false, true)
        val token = AccessToken("forced", null)
        provider.requests[1].second.complete(token)
        assertThat(joined.get(2, TimeUnit.SECONDS)).isSameAs(token)
        assertThat(cache.getAsync(url, false).get(2, TimeUnit.SECONDS)).isSameAs(token)
    }

    @Test
    fun cancellingOneWaiterDoesNotReplaceProviderFailureForOthers() {
        val provider = ControlledProvider()
        val cache = CachingAccessTokenProvider(provider)
        val cancelled = cache.getAsync(url, false)
        val waiting = cache.getAsync(url, false)
        cancelled.cancel(false)
        val error = IllegalStateException("exchange unavailable")
        provider.requests.single().second.completeExceptionally(error)
        assertThatThrownBy { waiting.get(2, TimeUnit.SECONDS) }
            .isInstanceOf(ExecutionException::class.java)
            .hasCause(error)
        val retry = cache.getAsync(url, false)
        provider.requests[1].second.complete(AccessToken("retry", null))
        assertThat(retry.get(2, TimeUnit.SECONDS).token).isEqualTo("retry")
    }

    @Test
    fun callerCompletionCannotSubstituteATokenForOtherWaiters() {
        val provider = ControlledProvider()
        val cache = CachingAccessTokenProvider(provider)
        val first = cache.getAsync(url, false)
        val other = cache.getAsync(url, false)
        first.complete(AccessToken("caller-local", null))
        assertThat(other.isDone).isFalse()
        provider.requests.single().second.complete(AccessToken("provider", null))
        assertThat(other.get(2, TimeUnit.SECONDS).token).isEqualTo("provider")
        assertThat(cache.getAsync(url, false).get(2, TimeUnit.SECONDS).token).isEqualTo("provider")
    }

    @Test
    fun cancellingAdvisoryResultDoesNotCancelBackgroundRefresh() {
        val now = Instant.parse("2026-01-01T00:00:00Z")
        val provider = ControlledProvider()
        val cache = CachingAccessTokenProvider(provider, clock = { now })
        val first = cache.getAsync(url, false)
        provider.requests.single().second.complete(AccessToken("stale", now.plusSeconds(60)))
        assertThat(first.get(2, TimeUnit.SECONDS).token).isEqualTo("stale")
        val advisory = cache.getAsync(url, false)
        assertThat(advisory.cancel(false)).isFalse()
        provider.requests[1].second.complete(AccessToken("fresh", null))
        assertThat(cache.getAsync(url, false).get(2, TimeUnit.SECONDS).token).isEqualTo("fresh")
    }
}
