package kotlinx.kmp.util.core.http

import java.util.concurrent.CompletableFuture
import kotlinx.kmp.util.core.RequestOptions

interface HttpClient : AutoCloseable {

    /** Blocking execution. */
    fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse

    /** Async execution returning CompletableFuture (JVM standard). */
    fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): java.util.concurrent.CompletableFuture<HttpResponse>

    /** Suspend-native async execution. Defaults to blocking execute(). */
    suspend fun executeSuspend(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): HttpResponse = execute(request, requestOptions)

    override fun close()
}
