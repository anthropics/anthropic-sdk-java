package com.anthropic.core.http

import com.anthropic.core.RequestOptions
import com.anthropic.internal.core.http.HeaderAuthHttpClient
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicBoolean
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class HeaderAuthHttpClientTest {

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun requestWithoutAuthHeadersGetsApiKeyAdded(async: Boolean) {
        val executedRequests = mutableListOf<HttpRequest>()
        val httpClient = createMockHttpClient(executedRequests)

        val authClient = HeaderAuthHttpClient(httpClient, "sk-test-key", null)

        val response = authClient.execute(createRequest(), async)

        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(executedRequests).hasSize(1)
        assertThat(executedRequests[0].headers.values("X-Api-Key")).containsExactly("sk-test-key")
        assertThat(executedRequests[0].headers.names()).doesNotContain("Authorization")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun requestWithoutAuthHeadersGetsAuthTokenAdded(async: Boolean) {
        val executedRequests = mutableListOf<HttpRequest>()
        val httpClient = createMockHttpClient(executedRequests)

        val authClient = HeaderAuthHttpClient(httpClient, null, "test-token")

        val response = authClient.execute(createRequest(), async)

        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(executedRequests).hasSize(1)
        assertThat(executedRequests[0].headers.values("Authorization"))
            .containsExactly("Bearer test-token")
        assertThat(executedRequests[0].headers.names()).doesNotContain("X-Api-Key")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun requestWithApiKeyHeaderPassesThrough(async: Boolean) {
        val executedRequests = mutableListOf<HttpRequest>()
        val httpClient = createMockHttpClient(executedRequests)

        val authClient = HeaderAuthHttpClient(httpClient, "sk-test-key", null)

        val request = createRequest().toBuilder().putHeader("X-Api-Key", "sk-existing-key").build()
        authClient.execute(request, async)

        assertThat(executedRequests).hasSize(1)
        assertThat(executedRequests[0].headers.values("X-Api-Key"))
            .containsExactly("sk-existing-key")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun requestWithAuthorizationHeaderPassesThrough(async: Boolean) {
        val executedRequests = mutableListOf<HttpRequest>()
        val httpClient = createMockHttpClient(executedRequests)

        val authClient = HeaderAuthHttpClient(httpClient, "sk-test-key", null)

        val request =
            createRequest().toBuilder().putHeader("Authorization", "Bearer existing-token").build()
        authClient.execute(request, async)

        assertThat(executedRequests).hasSize(1)
        assertThat(executedRequests[0].headers.values("Authorization"))
            .containsExactly("Bearer existing-token")
        assertThat(executedRequests[0].headers.names()).doesNotContain("X-Api-Key")
    }

    @Test
    fun closeDelegatesToUnderlyingClient() {
        val closed = AtomicBoolean(false)
        val httpClient =
            object : HttpClient {
                override fun execute(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): HttpResponse = throw UnsupportedOperationException()

                override fun executeAsync(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): CompletableFuture<HttpResponse> = throw UnsupportedOperationException()

                override fun close() {
                    closed.set(true)
                }
            }

        HeaderAuthHttpClient(httpClient, "sk-test-key", null).close()

        assertThat(closed.get()).isTrue()
    }

    private fun createRequest(): HttpRequest =
        HttpRequest.builder()
            .method(HttpMethod.POST)
            .baseUrl("https://api.anthropic.com")
            .addPathSegment("messages")
            .build()

    private fun createMockHttpClient(executedRequests: MutableList<HttpRequest>): HttpClient =
        object : HttpClient {
            override fun execute(
                request: HttpRequest,
                requestOptions: RequestOptions,
            ): HttpResponse {
                executedRequests.add(request)
                return object : HttpResponse {
                    override fun statusCode(): Int = 200

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

            override fun close() {}
        }

    private fun HttpClient.execute(request: HttpRequest, async: Boolean): HttpResponse =
        if (async) executeAsync(request).get() else execute(request)
}
