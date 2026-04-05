package kotlinx.kmp.util.core.http

import kotlinx.kmp.util.core.RequestOptions

interface HttpClient : AutoCloseable {

    /** Blocking execution. */
    fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse

    /** Suspend-native async execution. Defaults to blocking execute(). */
    suspend fun executeSuspend(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): HttpResponse = execute(request, requestOptions)

    override fun close()
}
