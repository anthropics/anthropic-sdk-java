// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core.http

import com.anthropic.core.RequestOptions

/** A functional interface that synchronously executes a request using a client. */
fun interface HttpClientExecute {

    fun execute(
        nextClient: HttpClient,
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): HttpResponse
}
