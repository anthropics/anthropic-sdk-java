// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core.http

import com.anthropic.core.RequestOptions

fun interface HttpClientExecute {

    fun execute(
        httpClient: HttpClient,
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): HttpResponse
}
