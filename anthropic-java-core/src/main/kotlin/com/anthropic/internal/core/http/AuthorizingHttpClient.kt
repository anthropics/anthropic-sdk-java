package com.anthropic.internal.core.http

import com.anthropic.core.RequestOptions
import com.anthropic.core.auth.AccessTokenProvider
import com.anthropic.core.auth.CachingAccessTokenProvider
import com.anthropic.core.checkRequired
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import java.util.concurrent.CompletableFuture

/**
 * Middleware [HttpClient] that attaches an authorization token obtained from an
 * [AccessTokenProvider] (via [CachingAccessTokenProvider]) to every outgoing request.
 *
 * If the server responds with 401, the cached token is invalidated and the request is retried once
 * with a freshly-fetched token.
 */
internal class AuthorizingHttpClient
private constructor(
    private val httpClient: HttpClient,
    private val tokenCache: CachingAccessTokenProvider,
    private val defaultBaseUrl: String,
    private val workspaceId: String?,
) : HttpClient {

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
        if (hasAuthorizationHeader(request)) {
            return httpClient.execute(request, requestOptions)
        }

        val authorizedRequest = addAuthorizationHeaders(request, forceRefresh = false)
        val response = httpClient.execute(authorizedRequest, requestOptions)

        if (response.statusCode() == 401 && isRetryable(request)) {
            response.close()
            tokenCache.invalidate()
            val refreshedRequest = addAuthorizationHeaders(request, forceRefresh = true)
            return httpClient.execute(refreshedRequest, requestOptions)
        }

        return response
    }

    override fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> {
        if (hasAuthorizationHeader(request)) {
            return httpClient.executeAsync(request, requestOptions)
        }

        return addAuthorizationHeadersAsync(request, forceRefresh = false)
            .thenCompose { authorizedRequest ->
                httpClient.executeAsync(authorizedRequest, requestOptions)
            }
            .thenCompose { response ->
                if (response.statusCode() == 401 && isRetryable(request)) {
                    response.close()
                    tokenCache.invalidate()
                    addAuthorizationHeadersAsync(request, forceRefresh = true).thenCompose {
                        refreshedRequest ->
                        httpClient.executeAsync(refreshedRequest, requestOptions)
                    }
                } else {
                    CompletableFuture.completedFuture(response)
                }
            }
    }

    override fun close() {
        httpClient.close()
    }

    private fun hasAuthorizationHeader(request: HttpRequest): Boolean =
        request.headers.names().contains(HEADER_AUTHORIZATION) ||
            request.headers.names().contains(HEADER_API_KEY)

    private fun isRetryable(request: HttpRequest): Boolean = request.body?.repeatable() ?: true

    private fun getBaseUrl(request: HttpRequest): String = request.baseUrl ?: defaultBaseUrl

    private fun addAuthorizationHeaders(request: HttpRequest, forceRefresh: Boolean): HttpRequest {
        val token = tokenCache.get(getBaseUrl(request), forceRefresh)
        return buildAuthorizedRequest(request, token.token)
    }

    private fun addAuthorizationHeadersAsync(
        request: HttpRequest,
        forceRefresh: Boolean,
    ): CompletableFuture<HttpRequest> {
        return tokenCache.getAsync(getBaseUrl(request), forceRefresh).thenApply { token ->
            buildAuthorizedRequest(request, token.token)
        }
    }

    private fun buildAuthorizedRequest(request: HttpRequest, token: String): HttpRequest {
        val existingBeta = request.headers.values(HEADER_BETA)
        val builder = request.toBuilder().putHeader(HEADER_AUTHORIZATION, "Bearer $token")
        if (existingBeta.isEmpty()) {
            builder.putHeader(HEADER_BETA, OAUTH_BETA)
        } else {
            builder.replaceHeaders(HEADER_BETA, (existingBeta + OAUTH_BETA).joinToString(","))
        }
        if (workspaceId != null) {
            builder.putHeader(HEADER_WORKSPACE_ID, workspaceId)
        }
        return builder.build()
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_API_KEY = "X-Api-Key"
        private const val HEADER_BETA = "anthropic-beta"
        private const val HEADER_WORKSPACE_ID = "anthropic-workspace-id"
        private const val OAUTH_BETA = "oauth-2025-04-20"
        private const val DEFAULT_BASE_URL = "https://api.anthropic.com"

        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var httpClient: HttpClient? = null
        private var tokenProvider: AccessTokenProvider? = null
        private var defaultBaseUrl: String = DEFAULT_BASE_URL
        private var workspaceId: String? = null

        fun httpClient(httpClient: HttpClient) = apply { this.httpClient = httpClient }

        fun tokenProvider(tokenProvider: AccessTokenProvider) = apply {
            this.tokenProvider = tokenProvider
        }

        fun defaultBaseUrl(defaultBaseUrl: String) = apply { this.defaultBaseUrl = defaultBaseUrl }

        fun workspaceId(workspaceId: String?) = apply { this.workspaceId = workspaceId }

        fun build(): HttpClient =
            AuthorizingHttpClient(
                checkRequired("httpClient", httpClient),
                CachingAccessTokenProvider(checkRequired("tokenProvider", tokenProvider)),
                defaultBaseUrl,
                workspaceId,
            )
    }
}
