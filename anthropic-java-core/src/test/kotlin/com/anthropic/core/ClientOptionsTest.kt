// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core

import com.anthropic.core.auth.CredentialResult
import com.anthropic.core.auth.InMemoryAccessTokenProvider
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.Interceptor
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
internal class ClientOptionsTest {

    private val httpClient = mock<HttpClient>()

    @Test
    fun putHeader_canOverwriteDefaultHeader() {
        val clientOptions =
            ClientOptions.builder()
                .httpClient(httpClient)
                .putHeader("User-Agent", "My User Agent")
                .build()

        assertThat(clientOptions.headers.values("User-Agent")).containsExactly("My User Agent")
    }

    @Test
    fun addInterceptor_appendsToInterceptors() {
        val first = Interceptor { it }
        val second = Interceptor { it }

        val clientOptions =
            ClientOptions.builder()
                .httpClient(httpClient)
                .addInterceptor(first)
                .addInterceptor(second)
                .build()

        assertThat(clientOptions.interceptors).containsExactly(first, second)
    }

    @Test
    fun toBuilder_interceptorsArePreserved() {
        val interceptor = Interceptor { it }
        var clientOptions =
            ClientOptions.builder().httpClient(httpClient).addInterceptor(interceptor).build()

        clientOptions = clientOptions.toBuilder().build()

        assertThat(clientOptions.interceptors).containsExactly(interceptor)
    }

    @Test
    fun toBuilder_whenOriginalClientOptionsGarbageCollected_doesNotCloseOriginalClient() {
        var clientOptions = ClientOptions.builder().httpClient(httpClient).build()
        verify(httpClient, never()).close()

        // Overwrite the `clientOptions` variable so that the original `ClientOptions` is GC'd.
        clientOptions = clientOptions.toBuilder().build()
        System.gc()
        Thread.sleep(100)

        verify(httpClient, never()).close()
        // This exists so that `clientOptions` is still reachable.
        assertThat(clientOptions).isEqualTo(clientOptions)
    }

    @Test
    fun interceptorObservesStaticCredentials() {
        // The 1p auth layer wraps the interceptors, so they see the logical credentials.
        val rawHttpClient = FakeHttpClient()
        val interceptedRequests = mutableListOf<HttpRequest>()
        val clientOptions =
            ClientOptions.builder()
                .httpClient(rawHttpClient)
                .addInterceptor(recordingInterceptor(interceptedRequests))
                .credentials(CredentialResult.staticHeaders("sk-test-key", null))
                .build()

        clientOptions.httpClient.execute(simpleGetRequest(), RequestOptions.none()).close()

        assertThat(interceptedRequests).hasSize(1)
        assertThat(interceptedRequests[0].headers.values("X-Api-Key"))
            .containsExactly("sk-test-key")
        assertThat(rawHttpClient.requests).hasSize(1)
        assertThat(rawHttpClient.requests[0].headers.values("X-Api-Key"))
            .containsExactly("sk-test-key")
    }

    @Test
    fun interceptorObservesTokenCredentials() {
        val rawHttpClient = FakeHttpClient()
        val interceptedRequests = mutableListOf<HttpRequest>()
        val clientOptions =
            ClientOptions.builder()
                .httpClient(rawHttpClient)
                .addInterceptor(recordingInterceptor(interceptedRequests))
                .credentials(CredentialResult.token(InMemoryAccessTokenProvider("oauth-token-123")))
                .build()

        clientOptions.httpClient.execute(simpleGetRequest(), RequestOptions.none()).close()

        assertThat(interceptedRequests).hasSize(1)
        assertThat(interceptedRequests[0].headers.values("Authorization"))
            .containsExactly("Bearer oauth-token-123")
        assertThat(interceptedRequests[0].headers.values("anthropic-beta"))
            .containsExactly("oauth-2025-04-20")
    }

    @Test
    fun interceptorObservesNoAuthHeadersWithoutCredentials() {
        val rawHttpClient = FakeHttpClient()
        val interceptedRequests = mutableListOf<HttpRequest>()
        val clientOptions =
            ClientOptions.builder()
                .httpClient(rawHttpClient)
                .addInterceptor(recordingInterceptor(interceptedRequests))
                .build()

        clientOptions.httpClient.execute(simpleGetRequest(), RequestOptions.none()).close()

        assertThat(interceptedRequests).hasSize(1)
        assertThat(interceptedRequests[0].headers.names()).doesNotContain("X-Api-Key")
        assertThat(interceptedRequests[0].headers.names()).doesNotContain("Authorization")
    }

    @Test
    fun interceptorCanReplaceCredentials() {
        val rawHttpClient = FakeHttpClient()
        val clientOptions =
            ClientOptions.builder()
                .httpClient(rawHttpClient)
                .addInterceptor(
                    requestMappingInterceptor {
                        it.toBuilder()
                            .replaceHeaders("Authorization", "Bearer custom-token")
                            .build()
                    }
                )
                .credentials(CredentialResult.staticHeaders(null, "original-token"))
                .build()

        clientOptions.httpClient.execute(simpleGetRequest(), RequestOptions.none()).close()

        assertThat(rawHttpClient.requests).hasSize(1)
        assertThat(rawHttpClient.requests[0].headers.values("Authorization"))
            .containsExactly("Bearer custom-token")
    }

    @Test
    fun requestWithAuthHeaderSkipsCredentials() {
        // Headers set at the client or request level are merged before the auth layer, which
        // must not override them.
        val rawHttpClient = FakeHttpClient()
        val clientOptions =
            ClientOptions.builder()
                .httpClient(rawHttpClient)
                .credentials(CredentialResult.staticHeaders("sk-test-key", null))
                .build()

        val request =
            simpleGetRequest().toBuilder().putHeader("X-Api-Key", "sk-existing-key").build()
        clientOptions.httpClient.execute(request, RequestOptions.none()).close()

        assertThat(rawHttpClient.requests).hasSize(1)
        assertThat(rawHttpClient.requests[0].headers.values("X-Api-Key"))
            .containsExactly("sk-existing-key")
    }

    private fun recordingInterceptor(requests: MutableList<HttpRequest>): Interceptor =
        requestMappingInterceptor {
            requests.add(it)
            it
        }

    private fun requestMappingInterceptor(onRequest: (HttpRequest) -> HttpRequest): Interceptor =
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
            .baseUrl("https://api.anthropic.com")
            .addPathSegment("v1")
            .addPathSegment("models")
            .build()

    private class FakeHttpClient : HttpClient {
        val requests = mutableListOf<HttpRequest>()

        override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
            requests.add(request)
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
}
