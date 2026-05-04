package com.anthropic.core.auth

import java.util.concurrent.CompletableFuture

internal class InMemoryIdentityTokenProvider(private val token: String) : IdentityTokenProvider {
    override fun get(): String = token

    override fun getAsync(): CompletableFuture<String> = CompletableFuture.completedFuture(token)
}
