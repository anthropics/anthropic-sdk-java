package com.anthropic.core

import com.anthropic.core.http.*
import java.io.InputStream
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*

@ExtendWith(MockitoExtension::class)
internal class InterceptingHttpClientTest {

    companion object {

        private val HTTP_REQUEST =
            HttpRequest.builder().method(HttpMethod.GET).baseUrl("https://example.com").build()

        private val THROWING_INTERCEPTOR =
            object : Interceptor {

                override fun intercept(chain: Interceptor.Chain): HttpResponse =
                    throw AssertionError()

                override fun interceptAsync(
                    chain: Interceptor.AsyncChain
                ): CompletableFuture<HttpResponse> = throw AssertionError()
            }
    }

    private val httpClient = mock<HttpClient>()

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_noInterceptors(async: Boolean) {
        val response = httpResponse("hello")
        mockClient(HTTP_REQUEST to listOf(response))
        val interceptingHttpClient = InterceptingHttpClient(httpClient, interceptors = listOf())

        val actualResponse = interceptingHttpClient.execute(HTTP_REQUEST, async)

        assertThat(actualResponse).isEqualTo(response)
        verify(httpClient, atMost(1)).execute(eqRequest(HTTP_REQUEST), any())
        verify(httpClient, atMost(1)).executeAsync(eqRequest(HTTP_REQUEST), any())
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_avoidSendingRequest(async: Boolean) {
        val interceptedResponse = httpResponse("INTERCEPTED!")
        val interceptingHttpClient =
            InterceptingHttpClient(
                httpClient,
                interceptors =
                    listOf(
                        object : Interceptor {

                            override fun intercept(chain: Interceptor.Chain): HttpResponse =
                                interceptedResponse

                            override fun interceptAsync(
                                chain: Interceptor.AsyncChain
                            ): CompletableFuture<HttpResponse> =
                                CompletableFuture.completedFuture(interceptedResponse)
                        },
                        // We shouldn't move onto the next interceptor after the previous one so we
                        // add this one to assert that the next one isn't called.
                        THROWING_INTERCEPTOR,
                    ),
            )

        val response = interceptingHttpClient.execute(HTTP_REQUEST, async)

        assertThat(response).isEqualTo(interceptedResponse)
        verify(httpClient, never()).execute(any())
        verify(httpClient, never()).executeAsync(any())
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_transformRequest(async: Boolean) {
        fun transformRequest(request: HttpRequest) =
            request.toBuilder().addPathSegment("transformed").build()
        val response = httpResponse("hello")
        mockClient(transformRequest(HTTP_REQUEST) to listOf(response))
        val interceptingHttpClient =
            InterceptingHttpClient(
                httpClient,
                interceptors =
                    listOf(
                        object : Interceptor {

                            override fun intercept(chain: Interceptor.Chain): HttpResponse =
                                chain.proceed(transformRequest(chain.request()))

                            override fun interceptAsync(
                                chain: Interceptor.AsyncChain
                            ): CompletableFuture<HttpResponse> =
                                chain.proceed(transformRequest(chain.request()))
                        }
                    ),
            )

        val actualResponse = interceptingHttpClient.execute(HTTP_REQUEST, async)

        assertThat(actualResponse).isEqualTo(response)
        verify(httpClient, atMost(1)).execute(eqRequest(transformRequest(HTTP_REQUEST)), any())
        verify(httpClient, atMost(1)).executeAsync(eqRequest(transformRequest(HTTP_REQUEST)), any())
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_transformResponse(async: Boolean) {
        val response = httpResponse("hello")
        mockClient(HTTP_REQUEST to listOf(response))
        val interceptingHttpClient =
            InterceptingHttpClient(
                httpClient,
                interceptors =
                    listOf(
                        object : Interceptor {

                            override fun intercept(chain: Interceptor.Chain): HttpResponse =
                                chain.proceed(chain.request()).mapBody { "$it world" }

                            override fun interceptAsync(
                                chain: Interceptor.AsyncChain
                            ): CompletableFuture<HttpResponse> =
                                chain.proceed(chain.request()).thenApply {
                                    it.mapBody { "$it world" }
                                }
                        }
                    ),
            )

        val actualResponse = interceptingHttpClient.execute(HTTP_REQUEST, async)

        assertThat(actualResponse.body().readBytes().toString(Charsets.UTF_8))
            .isEqualTo("hello world")
        verify(httpClient, atMost(1)).execute(eqRequest(HTTP_REQUEST), any())
        verify(httpClient, atMost(1)).executeAsync(eqRequest(HTTP_REQUEST), any())
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_multipleRequests(async: Boolean) {
        val response = httpResponse("hello")
        mockClient(HTTP_REQUEST to listOf(response))
        val interceptingHttpClient =
            InterceptingHttpClient(
                httpClient,
                interceptors =
                    listOf(
                        object : Interceptor {

                            override fun intercept(chain: Interceptor.Chain): HttpResponse {
                                val response1 = chain.proceed(chain.request())
                                val response2 = chain.proceed(chain.request())
                                return response1.mapBody {
                                    "$it ${response2.body().readBytes().toString(Charsets.UTF_8)}"
                                }
                            }

                            override fun interceptAsync(
                                chain: Interceptor.AsyncChain
                            ): CompletableFuture<HttpResponse> {
                                val responseFuture1 = chain.proceed(chain.request())
                                val responseFuture2 = chain.proceed(chain.request())
                                return responseFuture1.thenCombine(responseFuture2) {
                                    response1,
                                    response2 ->
                                    response1.mapBody {
                                        "$it ${response2.body().readBytes().toString(Charsets.UTF_8)}"
                                    }
                                }
                            }
                        }
                    ),
            )

        val actualResponse = interceptingHttpClient.execute(HTTP_REQUEST, async)

        assertThat(actualResponse.body().readBytes().toString(Charsets.UTF_8))
            .isEqualTo("hello hello")
        verify(httpClient, atMost(2)).execute(eqRequest(HTTP_REQUEST), any())
        verify(httpClient, atMost(2)).executeAsync(eqRequest(HTTP_REQUEST), any())
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_multipleInterceptors(async: Boolean) {
        val response = httpResponse("hello")
        mockClient(HTTP_REQUEST to listOf(response))
        val interceptingHttpClient =
            InterceptingHttpClient(
                httpClient,
                interceptors =
                    listOf(
                        object : Interceptor {

                            override fun intercept(chain: Interceptor.Chain): HttpResponse {
                                val response1 = chain.proceed(chain.request())
                                val response2 = chain.proceed(chain.request())
                                return response1.mapBody {
                                    "$it ${response2.body().readBytes().toString(Charsets.UTF_8)}"
                                }
                            }

                            override fun interceptAsync(
                                chain: Interceptor.AsyncChain
                            ): CompletableFuture<HttpResponse> {
                                val responseFuture1 = chain.proceed(chain.request())
                                val responseFuture2 = chain.proceed(chain.request())
                                return responseFuture1.thenCombine(responseFuture2) {
                                    response1,
                                    response2 ->
                                    response1.mapBody {
                                        "$it ${response2.body().readBytes().toString(Charsets.UTF_8)}"
                                    }
                                }
                            }
                        },
                        object : Interceptor {

                            override fun intercept(chain: Interceptor.Chain): HttpResponse =
                                chain.proceed(chain.request()).mapBody { "$it world" }

                            override fun interceptAsync(
                                chain: Interceptor.AsyncChain
                            ): CompletableFuture<HttpResponse> =
                                chain.proceed(chain.request()).thenApply {
                                    it.mapBody { "$it world" }
                                }
                        },
                    ),
            )

        val actualResponse = interceptingHttpClient.execute(HTTP_REQUEST, async)

        assertThat(actualResponse.body().readBytes().toString(Charsets.UTF_8))
            .isEqualTo("hello world hello world")
        verify(httpClient, atMost(2)).execute(eqRequest(HTTP_REQUEST), any())
        verify(httpClient, atMost(2)).executeAsync(eqRequest(HTTP_REQUEST), any())
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_multipleInterceptorsAndCalls(async: Boolean) {
        val response = httpResponse("hello")
        mockClient(HTTP_REQUEST to listOf(response))
        val interceptingHttpClient =
            InterceptingHttpClient(
                httpClient,
                interceptors =
                    listOf(
                        object : Interceptor {

                            override fun intercept(chain: Interceptor.Chain): HttpResponse =
                                chain.proceed(chain.request()).mapBody { "$it!" }

                            override fun interceptAsync(
                                chain: Interceptor.AsyncChain
                            ): CompletableFuture<HttpResponse> =
                                chain.proceed(chain.request()).thenApply { it.mapBody { "$it!" } }
                        },
                        object : Interceptor {

                            override fun intercept(chain: Interceptor.Chain): HttpResponse =
                                chain.proceed(chain.request()).mapBody { "$it world" }

                            override fun interceptAsync(
                                chain: Interceptor.AsyncChain
                            ): CompletableFuture<HttpResponse> =
                                chain.proceed(chain.request()).thenApply {
                                    it.mapBody { "$it world" }
                                }
                        },
                    ),
            )

        val actualResponse = interceptingHttpClient.execute(HTTP_REQUEST, async)

        assertThat(actualResponse.body().readBytes().toString(Charsets.UTF_8))
            .isEqualTo("hello world!")
        verify(httpClient, atMost(1)).execute(eqRequest(HTTP_REQUEST), any())
        verify(httpClient, atMost(1)).executeAsync(eqRequest(HTTP_REQUEST), any())
    }

    private fun HttpClient.execute(request: HttpRequest, async: Boolean): HttpResponse =
        if (async) executeAsync(request).get() else execute(request)

    private fun mockClient(vararg requestToResponses: Pair<HttpRequest, List<HttpResponse>>) {
        for ((request, responses) in requestToResponses) {
            whenever(httpClient.execute(eqRequest(request), any()))
                .thenReturn(responses.first(), *responses.subList(1, responses.size).toTypedArray())
            whenever(httpClient.executeAsync(eqRequest(request), any()))
                .thenReturn(
                    CompletableFuture.completedFuture(responses.first()),
                    *responses
                        .subList(1, responses.size)
                        .map { CompletableFuture.completedFuture(it) }
                        .toTypedArray(),
                )
        }
    }

    private fun eqRequest(request: HttpRequest) =
        argThat<HttpRequest> { method == request.method && pathSegments == request.pathSegments }

    private fun httpResponse(body: String): HttpResponse =
        object : HttpResponse {

            override fun statusCode(): Int = 0

            override fun headers(): Headers = Headers.builder().build()

            override fun body(): InputStream = body.byteInputStream()

            override fun close() {}
        }

    private fun HttpResponse.mapBody(transform: (String) -> String): HttpResponse =
        object : HttpResponse {

            override fun statusCode(): Int = this@mapBody.statusCode()

            override fun headers(): Headers = this@mapBody.headers()

            override fun body(): InputStream =
                transform(this@mapBody.body().readBytes().toString(Charsets.UTF_8))
                    .byteInputStream()

            override fun close() = this@mapBody.close()
        }
}
