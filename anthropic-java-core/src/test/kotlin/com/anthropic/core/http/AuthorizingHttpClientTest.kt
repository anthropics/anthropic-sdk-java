package com.anthropic.core.http

import com.anthropic.core.RequestOptions
import com.anthropic.core.auth.AccessToken
import com.anthropic.core.auth.AccessTokenProvider
import com.anthropic.internal.core.http.AuthorizingHttpClient
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class AuthorizingHttpClientTest {

    private var openResponseCount = 0

    @BeforeEach
    fun beforeEach() {
        openResponseCount = 0
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun requestWithAuthorizationHeaderPassesThrough(async: Boolean) {
        val requestedForceRefresh = mutableListOf<Boolean>()
        val tokenProvider = createTokenProvider("test-token") { requestedForceRefresh.add(it) }
        val executedRequests = mutableListOf<HttpRequest>()
        val httpClient = createMockHttpClient(200, executedRequests)

        val authorizingClient =
            AuthorizingHttpClient.builder()
                .httpClient(httpClient)
                .tokenProvider(tokenProvider)
                .build()

        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://api.anthropic.com")
                .addPathSegment("messages")
                .putHeader("Authorization", "Bearer existing-token")
                .build()

        val response = authorizingClient.execute(request, async)

        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(executedRequests).hasSize(1)
        assertThat(executedRequests[0].headers.values("Authorization"))
            .containsExactly("Bearer existing-token")
        assertThat(executedRequests[0].headers.names()).doesNotContain("anthropic-beta")
        assertThat(requestedForceRefresh).isEmpty()
        response.close()
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun requestWithApiKeyHeaderPassesThrough(async: Boolean) {
        val requestedForceRefresh = mutableListOf<Boolean>()
        val tokenProvider = createTokenProvider("test-token") { requestedForceRefresh.add(it) }
        val executedRequests = mutableListOf<HttpRequest>()
        val httpClient = createMockHttpClient(200, executedRequests)

        val authorizingClient =
            AuthorizingHttpClient.builder()
                .httpClient(httpClient)
                .tokenProvider(tokenProvider)
                .build()

        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://api.anthropic.com")
                .addPathSegment("messages")
                .putHeader("X-Api-Key", "sk-existing-key")
                .build()

        val response = authorizingClient.execute(request, async)

        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(executedRequests).hasSize(1)
        assertThat(executedRequests[0].headers.values("X-Api-Key"))
            .containsExactly("sk-existing-key")
        assertThat(executedRequests[0].headers.names()).doesNotContain("Authorization")
        assertThat(executedRequests[0].headers.names()).doesNotContain("anthropic-beta")
        assertThat(requestedForceRefresh).isEmpty()
        response.close()
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun requestWithoutAuthHeadersGetsAuthorizationAdded(async: Boolean) {
        val requestedForceRefresh = mutableListOf<Boolean>()
        val tokenProvider = createTokenProvider("oauth-token-123") { requestedForceRefresh.add(it) }
        val executedRequests = mutableListOf<HttpRequest>()
        val httpClient = createMockHttpClient(200, executedRequests)

        val authorizingClient =
            AuthorizingHttpClient.builder()
                .httpClient(httpClient)
                .tokenProvider(tokenProvider)
                .build()

        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://api.anthropic.com")
                .addPathSegment("messages")
                .build()

        val response = authorizingClient.execute(request, async)

        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(executedRequests).hasSize(1)
        assertThat(executedRequests[0].headers.values("Authorization"))
            .containsExactly("Bearer oauth-token-123")
        assertThat(executedRequests[0].headers.values("anthropic-beta"))
            .containsExactly("oauth-2025-04-20")
        assertThat(executedRequests[0].headers.names()).doesNotContain("anthropic-workspace-id")
        assertThat(requestedForceRefresh).containsExactly(false)
        response.close()
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun on401ResponseRetriesWithFreshToken(async: Boolean) {
        val requestedForceRefresh = mutableListOf<Boolean>()
        val tokenProvider =
            createCountingTokenProvider(listOf("expired-token", "fresh-token")) {
                requestedForceRefresh.add(it)
            }

        val executedRequests = mutableListOf<HttpRequest>()
        val httpClient = create401ThenOkHttpClient(executedRequests)

        val authorizingClient =
            AuthorizingHttpClient.builder()
                .httpClient(httpClient)
                .tokenProvider(tokenProvider)
                .build()

        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://api.anthropic.com")
                .addPathSegment("messages")
                .build()

        val response = authorizingClient.execute(request, async)

        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(executedRequests).hasSize(2)
        assertThat(executedRequests[0].headers.values("Authorization"))
            .containsExactly("Bearer expired-token")
        assertThat(executedRequests[1].headers.values("Authorization"))
            .containsExactly("Bearer fresh-token")
        assertThat(requestedForceRefresh).containsExactly(false, true)
        response.close()
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun on401WithNonRepeatableBodyDoesNotRetry(async: Boolean) {
        val tokenProvider = createTokenProvider("test-token")
        val executedRequests = mutableListOf<HttpRequest>()
        val httpClient = createMockHttpClient(401, executedRequests)

        val authorizingClient =
            AuthorizingHttpClient.builder()
                .httpClient(httpClient)
                .tokenProvider(tokenProvider)
                .build()

        val nonRepeatableBody =
            object : HttpRequestBody {
                override fun writeTo(outputStream: OutputStream) {}

                override fun contentType(): String = "application/json"

                override fun contentLength(): Long = 0L

                override fun repeatable(): Boolean = false

                override fun close() {}
            }

        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://api.anthropic.com")
                .addPathSegment("messages")
                .body(nonRepeatableBody)
                .build()

        val response = authorizingClient.execute(request, async)

        assertThat(response.statusCode()).isEqualTo(401)
        assertThat(executedRequests).hasSize(1)
        response.close()
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun on200ResponseDoesNotRetry(async: Boolean) {
        val requestedForceRefresh = mutableListOf<Boolean>()
        val tokenProvider = createTokenProvider("test-token") { requestedForceRefresh.add(it) }

        val executedRequests = mutableListOf<HttpRequest>()
        val httpClient = createMockHttpClient(200, executedRequests)

        val authorizingClient =
            AuthorizingHttpClient.builder()
                .httpClient(httpClient)
                .tokenProvider(tokenProvider)
                .build()

        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://api.anthropic.com")
                .addPathSegment("messages")
                .build()

        val response = authorizingClient.execute(request, async)

        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(executedRequests).hasSize(1)
        assertThat(requestedForceRefresh).containsExactly(false)
        response.close()
        assertNoResponseLeaks()
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

        val tokenProvider = createTokenProvider("test-token")

        val authorizingClient =
            AuthorizingHttpClient.builder()
                .httpClient(httpClient)
                .tokenProvider(tokenProvider)
                .build()

        authorizingClient.close()

        assertThat(closed.get()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun on401WithRepeatableBodyRetries(async: Boolean) {
        val requestedForceRefresh = mutableListOf<Boolean>()
        val tokenProvider =
            createCountingTokenProvider(listOf("expired-token", "fresh-token")) {
                requestedForceRefresh.add(it)
            }

        val executedRequests = mutableListOf<HttpRequest>()
        val httpClient = create401ThenOkHttpClient(executedRequests)

        val repeatableBody =
            object : HttpRequestBody {
                override fun writeTo(outputStream: OutputStream) {}

                override fun contentType(): String = "application/json"

                override fun contentLength(): Long = 0L

                override fun repeatable(): Boolean = true

                override fun close() {}
            }

        val authorizingClient =
            AuthorizingHttpClient.builder()
                .httpClient(httpClient)
                .tokenProvider(tokenProvider)
                .build()

        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://api.anthropic.com")
                .addPathSegment("messages")
                .body(repeatableBody)
                .build()

        val response = authorizingClient.execute(request, async)

        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(executedRequests).hasSize(2)
        assertThat(requestedForceRefresh).containsExactly(false, true)
        response.close()
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun requestWithNullBodyIsRetryable(async: Boolean) {
        val requestedForceRefresh = mutableListOf<Boolean>()
        val tokenProvider =
            createCountingTokenProvider(listOf("expired-token", "fresh-token")) {
                requestedForceRefresh.add(it)
            }

        val executedRequests = mutableListOf<HttpRequest>()
        val httpClient = create401ThenOkHttpClient(executedRequests)

        val authorizingClient =
            AuthorizingHttpClient.builder()
                .httpClient(httpClient)
                .tokenProvider(tokenProvider)
                .build()

        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl("https://api.anthropic.com")
                .addPathSegment("models")
                .build()

        val response = authorizingClient.execute(request, async)

        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(executedRequests).hasSize(2)
        assertThat(requestedForceRefresh).containsExactly(false, true)
        response.close()
        assertNoResponseLeaks()
    }

    private fun createTokenProvider(
        token: String,
        onGetToken: (Boolean) -> Unit = {},
    ): AccessTokenProvider =
        object : AccessTokenProvider {
            override fun get(baseUrl: String, forceRefresh: Boolean): AccessToken {
                onGetToken(forceRefresh)
                return AccessToken(token)
            }

            override fun getAsync(
                baseUrl: String,
                forceRefresh: Boolean,
            ): CompletableFuture<AccessToken> =
                CompletableFuture.completedFuture(get(baseUrl, forceRefresh))
        }

    private fun createCountingTokenProvider(
        tokens: List<String>,
        onGetToken: (Boolean) -> Unit = {},
    ): AccessTokenProvider {
        val callCount = AtomicInteger(0)
        return object : AccessTokenProvider {
            override fun get(baseUrl: String, forceRefresh: Boolean): AccessToken {
                onGetToken(forceRefresh)
                val index = callCount.getAndIncrement().coerceAtMost(tokens.size - 1)
                return AccessToken(tokens[index])
            }

            override fun getAsync(
                baseUrl: String,
                forceRefresh: Boolean,
            ): CompletableFuture<AccessToken> =
                CompletableFuture.completedFuture(get(baseUrl, forceRefresh))
        }
    }

    private fun createMockHttpClient(
        statusCode: Int,
        executedRequests: MutableList<HttpRequest>,
    ): HttpClient =
        object : HttpClient {
            override fun execute(
                request: HttpRequest,
                requestOptions: RequestOptions,
            ): HttpResponse {
                executedRequests.add(request)
                return trackClose(createMockResponse(statusCode))
            }

            override fun executeAsync(
                request: HttpRequest,
                requestOptions: RequestOptions,
            ): CompletableFuture<HttpResponse> =
                CompletableFuture.completedFuture(execute(request, requestOptions))

            override fun close() {}
        }

    private fun create401ThenOkHttpClient(executedRequests: MutableList<HttpRequest>): HttpClient {
        val responseCount = AtomicInteger(0)
        return object : HttpClient {
            override fun execute(
                request: HttpRequest,
                requestOptions: RequestOptions,
            ): HttpResponse {
                executedRequests.add(request)
                val count = responseCount.incrementAndGet()
                val statusCode = if (count == 1) 401 else 200
                return trackClose(createMockResponse(statusCode))
            }

            override fun executeAsync(
                request: HttpRequest,
                requestOptions: RequestOptions,
            ): CompletableFuture<HttpResponse> =
                CompletableFuture.completedFuture(execute(request, requestOptions))

            override fun close() {}
        }
    }

    private fun createMockResponse(statusCode: Int): HttpResponse =
        object : HttpResponse {
            override fun statusCode(): Int = statusCode

            override fun headers(): Headers = Headers.builder().build()

            override fun body(): InputStream = ByteArrayInputStream(ByteArray(0))

            override fun close() {}
        }

    private fun trackClose(response: HttpResponse): HttpResponse {
        openResponseCount++
        return object : HttpResponse {
            private var isClosed = false

            override fun statusCode(): Int = response.statusCode()

            override fun headers(): Headers = response.headers()

            override fun body(): InputStream = response.body()

            override fun close() {
                response.close()
                if (isClosed) {
                    return
                }
                openResponseCount--
                isClosed = true
            }
        }
    }

    private fun HttpClient.execute(request: HttpRequest, async: Boolean): HttpResponse =
        if (async) executeAsync(request).get() else execute(request)

    private fun assertNoResponseLeaks() = assertThat(openResponseCount).isEqualTo(0)
}
