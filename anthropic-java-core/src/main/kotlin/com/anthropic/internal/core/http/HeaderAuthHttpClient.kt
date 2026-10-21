package com.anthropic.internal.core.http

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import java.util.concurrent.CompletableFuture

/**
 * Middleware [HttpClient] that attaches the static first-party credential (an API key or an auth
 * token) to every outgoing request as `Authorization: Bearer`.
 *
 * Requests that already carry an `Authorization` or `X-Api-Key` header are passed through
 * unchanged, so credentials set at the client or request level take precedence.
 */
internal class HeaderAuthHttpClient(
    private val httpClient: HttpClient,
    private val credential: String,
) : HttpClient {

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse =
        httpClient.execute(request.withStaticAuthHeaders(credential), requestOptions)

    override fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> =
        httpClient.executeAsync(request.withStaticAuthHeaders(credential), requestOptions)

    override fun close() = httpClient.close()
}
