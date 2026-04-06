package kotlinx.kmp.util.core.http

import kotlinx.kmp.util.core.RequestOptions
import kotlinx.kmp.util.core.closeWhenPhantomReachable

/**
 * A delegating wrapper around an `HttpClient` that closes it once it's only phantom reachable.
 *
 * This class ensures the `HttpClient` is closed even if the user forgets to close it.
 */
class PhantomReachableClosingHttpClient(private val httpClient: HttpClient) : HttpClient {
    init {
        closeWhenPhantomReachable(this, httpClient)
    }

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse =
        httpClient.execute(request, requestOptions)

    override suspend fun executeSuspend(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): HttpResponse = httpClient.executeSuspend(request, requestOptions)

    override fun close() = httpClient.close()
}
