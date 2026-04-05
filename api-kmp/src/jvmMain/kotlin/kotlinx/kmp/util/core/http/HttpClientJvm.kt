@file:JvmName("HttpClientJvm")

package kotlinx.kmp.util.core.http

import kotlinx.kmp.util.core.RequestOptions
import kotlinx.coroutines.*
import java.util.concurrent.CompletableFuture

/**
 * JVM-only async execution returning [CompletableFuture].
 *
 * This is the JVM interop surface for code that cannot use `suspend`.
 * Common (KMP) code should call [HttpClient.executeSuspend] instead.
 */
fun HttpClient.executeAsync(
    request: HttpRequest,
    requestOptions: RequestOptions,
): CompletableFuture<HttpResponse> {
    val future = CompletableFuture<HttpResponse>()
    CoroutineScope(Dispatchers.IO).launch {
        try {
            future.complete(executeSuspend(request, requestOptions))
        } catch (e: Exception) {
            future.completeExceptionally(e)
        }
    }
    return future
}
