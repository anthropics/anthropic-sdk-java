package com.anthropic.core.auth

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

internal class InMemoryAccessTokenProvider(private val token: String) : AccessTokenProvider {
    override fun get(baseUrl: String, forceRefresh: Boolean): AccessToken = AccessToken(token)

    override fun getAsync(
        baseUrl: String,
        forceRefresh: Boolean,
        executor: Executor,
    ): CompletableFuture<AccessToken> = CompletableFuture.completedFuture(AccessToken(token))
}
