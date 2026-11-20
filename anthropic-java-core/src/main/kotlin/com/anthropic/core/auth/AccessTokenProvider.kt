package com.anthropic.core.auth

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

internal interface AccessTokenProvider {
    fun get(baseUrl: String, forceRefresh: Boolean = false): AccessToken

    fun getAsync(
        baseUrl: String,
        forceRefresh: Boolean = false,
        executor: Executor,
    ): CompletableFuture<AccessToken>
}
