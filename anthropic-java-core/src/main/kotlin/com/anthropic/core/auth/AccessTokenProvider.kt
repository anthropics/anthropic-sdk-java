package com.anthropic.core.auth

import java.util.concurrent.CompletableFuture

internal interface AccessTokenProvider {
    fun get(baseUrl: String, forceRefresh: Boolean = false): AccessToken

    fun getAsync(baseUrl: String, forceRefresh: Boolean = false): CompletableFuture<AccessToken>
}
