package com.anthropic.internal.core.http

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import java.util.concurrent.CompletableFuture

/**
 * Middleware [HttpClient] that attaches static first-party credentials (`X-Api-Key` or
 * `Authorization: Bearer`) to every outgoing request.
 *
 * Requests that already carry either header are passed through unchanged, so credentials set at the
 * client or request level take precedence.
 */
internal class HeaderAuthHttpClient(
    private val httpClient: HttpClient,
    private val apiKey: String?,
    private val authToken: String?,
) : HttpClient {

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse =
        httpClient.execute(request.withStaticAuthHeaders(apiKey, authToken), requestOptions)

    override fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> =
        httpClient.executeAsync(request.withStaticAuthHeaders(apiKey, authToken), requestOptions)

    override fun close() = httpClient.close()
}
