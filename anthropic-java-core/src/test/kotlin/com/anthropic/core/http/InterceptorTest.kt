// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core.http

import com.anthropic.core.RequestOptions
import com.anthropic.core.Sleeper
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.time.Duration
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class InterceptorTest {

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun intercept_appliesInterceptorsInOrder(async: Boolean) {
        val order = mutableListOf<String>()
        val httpClient = FakeHttpClient(order)
        val interceptedClient =
            listOf(
                    interceptor {
                        order.add("first")
                        it
                    },
                    interceptor {
                        order.add("second")
                        it
                    },
                )
                .intercept(httpClient)

        val response = interceptedClient.execute(simpleGetRequest(), async)

        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(order).containsExactly("first", "second", "underlying")
    }

    @Test
    fun intercept_withNoInterceptors_returnsUnderlyingClient() {
        val httpClient = FakeHttpClient()

        val interceptedClient = emptyList<Interceptor>().intercept(httpClient)

        assertThat(interceptedClient).isSameAs(httpClient)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun intercept_interceptorCanModifyRequest(async: Boolean) {
        val httpClient = FakeHttpClient()
        val interceptedClient =
            listOf(interceptor { it.toBuilder().putHeader("X-Custom", "value").build() })
                .intercept(httpClient)

        interceptedClient.execute(simpleGetRequest(), async)

        assertThat(httpClient.requests).hasSize(1)
        assertThat(httpClient.requests[0].headers.values("X-Custom")).containsExactly("value")
    }

    @Test
    fun intercept_closeDelegatesToUnderlyingClient() {
        val httpClient = FakeHttpClient()
        val interceptedClient = listOf(interceptor { it }).intercept(httpClient)

        interceptedClient.close()

        assertThat(httpClient.closed).isTrue()
    }

    @Test
    fun syncOnly_executeDelegates() {
        val order = mutableListOf<String>()
        val httpClient = FakeHttpClient(order)
        val interceptedClient =
            listOf(
                    Interceptor.syncOnly { nextClient, request, requestOptions ->
                        order.add("interceptor")
                        nextClient.execute(request, requestOptions)
                    }
                )
                .intercept(httpClient)

        val response = interceptedClient.execute(simpleGetRequest())

        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(order).containsExactly("interceptor", "underlying")
    }

    @Test
    fun syncOnly_executeAsyncThrows() {
        val interceptedClient =
            listOf(
                    Interceptor.syncOnly { nextClient, request, requestOptions ->
                        nextClient.execute(request, requestOptions)
                    }
                )
                .intercept(FakeHttpClient())

        assertThatThrownBy { interceptedClient.execute(simpleGetRequest(), async = true) }
            .isInstanceOf(UnsupportedOperationException::class.java)
    }

    @Test
    fun syncOnly_closeDelegatesToUnderlyingClient() {
        val httpClient = FakeHttpClient()
        val interceptedClient =
            listOf(
                    Interceptor.syncOnly { nextClient, request, requestOptions ->
                        nextClient.execute(request, requestOptions)
                    }
                )
                .intercept(httpClient)

        interceptedClient.close()

        assertThat(httpClient.closed).isTrue()
    }

    @Test
    fun asyncOnly_executeAsyncDelegates() {
        val order = mutableListOf<String>()
        val httpClient = FakeHttpClient(order)
        val interceptedClient =
            listOf(
                    Interceptor.asyncOnly { nextClient, request, requestOptions ->
                        order.add("interceptor")
                        nextClient.executeAsync(request, requestOptions)
                    }
                )
                .intercept(httpClient)

        val response = interceptedClient.execute(simpleGetRequest(), async = true)

        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(order).containsExactly("interceptor", "underlying")
    }

    @Test
    fun asyncOnly_executeThrows() {
        val interceptedClient =
            listOf(
                    Interceptor.asyncOnly { nextClient, request, requestOptions ->
                        nextClient.executeAsync(request, requestOptions)
                    }
                )
                .intercept(FakeHttpClient())

        assertThatThrownBy { interceptedClient.execute(simpleGetRequest()) }
            .isInstanceOf(UnsupportedOperationException::class.java)
    }

    @Test
    fun asyncOnly_closeDelegatesToUnderlyingClient() {
        val httpClient = FakeHttpClient()
        val interceptedClient =
            listOf(
                    Interceptor.asyncOnly { nextClient, request, requestOptions ->
                        nextClient.executeAsync(request, requestOptions)
                    }
                )
                .intercept(httpClient)

        interceptedClient.close()

        assertThat(httpClient.closed).isTrue()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun retryingHttpClient_interceptsEveryRetriedRequest(async: Boolean) {
        val order = mutableListOf<String>()
        val httpClient = FakeHttpClient(order, statusCodes = listOf(503, 200))
        val retryingClient =
            RetryingHttpClient.builder()
                .httpClient(
                    listOf(
                            interceptor {
                                order.add("interceptor")
                                it
                            }
                        )
                        .intercept(httpClient)
                )
                .sleeper(NoOpSleeper())
                .maxRetries(1)
                .build()

        val response = retryingClient.execute(simpleGetRequest(), async)

        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(order).containsExactly("interceptor", "underlying", "interceptor", "underlying")
    }

    private fun interceptor(onRequest: (HttpRequest) -> HttpRequest): Interceptor =
        Interceptor { client ->
            object : HttpClient {
                override fun execute(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): HttpResponse = client.execute(onRequest(request), requestOptions)

                override fun executeAsync(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): CompletableFuture<HttpResponse> =
                    client.executeAsync(onRequest(request), requestOptions)

                override fun close() = client.close()
            }
        }

    private fun simpleGetRequest(): HttpRequest =
        HttpRequest.builder()
            .method(HttpMethod.GET)
            .baseUrl("https://api.example.com")
            .addPathSegment("v1")
            .addPathSegment("resources")
            .build()

    private class FakeHttpClient(
        private val order: MutableList<String> = mutableListOf(),
        private val statusCodes: List<Int> = listOf(200),
    ) : HttpClient {
        val requests = mutableListOf<HttpRequest>()
        var closed = false
        private var callCount = 0

        override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
            order.add("underlying")
            requests.add(request)
            val statusCode = statusCodes[minOf(callCount++, statusCodes.size - 1)]
            return object : HttpResponse {
                override fun statusCode(): Int = statusCode

                override fun headers(): Headers = Headers.builder().build()

                override fun body(): InputStream = ByteArrayInputStream(ByteArray(0))

                override fun close() {}
            }
        }

        override fun executeAsync(
            request: HttpRequest,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            CompletableFuture.completedFuture(execute(request, requestOptions))

        override fun close() {
            closed = true
        }
    }

    private class NoOpSleeper : Sleeper {
        override fun sleep(duration: Duration) {}

        override fun sleepAsync(duration: Duration): CompletableFuture<Void> =
            CompletableFuture.completedFuture(null)

        override fun close() {}
    }

    private fun HttpClient.execute(request: HttpRequest, async: Boolean): HttpResponse =
        if (async) executeAsync(request).get() else execute(request)
}
