package com.anthropic.core.auth

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

// IdentityTokenProvider provides an identity token, which can be used by
// [WorkloadIdentityCredentials] to exchange it for an access token.
internal interface IdentityTokenProvider {
    fun get(): String

    fun getAsync(executor: Executor): CompletableFuture<String>
}
