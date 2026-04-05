package kotlinx.kmp.util.core

import kotlinx.kmp.util.core.http.HttpRequest

fun HttpRequest.prepare(clientOptions: ClientOptions, params: Params): HttpRequest =
    toBuilder()
        .putAllQueryParams(clientOptions.queryParams)
        .replaceAllQueryParams(params._queryParams())
        .putAllHeaders(clientOptions.headers)
        .replaceAllHeaders(params._headers())
        .build()

suspend fun HttpRequest.prepareSuspend(clientOptions: ClientOptions, params: Params): HttpRequest =
    prepare(clientOptions, params)
