@file:JvmName("PrepareRequestJvm")

package kotlinx.kmp.util.core

import kotlinx.kmp.util.core.http.HttpRequest
import java.util.concurrent.CompletableFuture

fun HttpRequest.prepareAsync(
    clientOptions: ClientOptions,
    params: Params,
): CompletableFuture<HttpRequest> =
    CompletableFuture.completedFuture(prepare(clientOptions, params))
