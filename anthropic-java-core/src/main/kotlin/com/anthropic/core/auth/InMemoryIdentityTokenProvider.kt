package com.anthropic.core.auth

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

internal class InMemoryIdentityTokenProvider(private val token: String) : IdentityTokenProvider {
    override fun get(): String = token

    override fun getAsync(executor: Executor): CompletableFuture<String> =
        CompletableFuture.completedFuture(token)
}
