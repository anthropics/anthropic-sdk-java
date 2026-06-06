package com.anthropic.client.okhttp

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.Interceptor
import com.github.tomakehurst.wiremock.client.WireMock.findAll
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

/**
 * Verifies the interceptor ordering contract end-to-end: interceptors observe the logical 1p
 * credentials (the auth layer wraps them), and the wire request carries the credential exactly once
 * (the backend's fallback authorization no-ops).
 */
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class InterceptorAuthE2ETest {

    private lateinit var baseUrl: String

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        baseUrl = wmRuntimeInfo.httpBaseUrl
        stubFor(get(urlPathEqualTo("/v1/models/claude-test")).willReturn(ok().withBody(MODEL_JSON)))
    }

    @Test
    fun interceptorObservesApiKey() {
        val interceptedRequests = mutableListOf<HttpRequest>()
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(baseUrl)
                .apiKey("sk-test-key")
                .addInterceptor(recordingInterceptor(interceptedRequests))
                .build()

        client.models().retrieve("claude-test")

        assertThat(interceptedRequests).hasSize(1)
        assertThat(interceptedRequests[0].headers.values("X-Api-Key"))
            .containsExactly("sk-test-key")
        assertSingleWireHeader("X-Api-Key", "sk-test-key")
    }

    @Test
    fun interceptorObservesAuthToken() {
        val interceptedRequests = mutableListOf<HttpRequest>()
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(baseUrl)
                .authToken("test-token")
                .addInterceptor(recordingInterceptor(interceptedRequests))
                .build()

        client.models().retrieve("claude-test")

        assertThat(interceptedRequests).hasSize(1)
        assertThat(interceptedRequests[0].headers.values("Authorization"))
            .containsExactly("Bearer test-token")
        assertSingleWireHeader("Authorization", "Bearer test-token")
    }

    @Test
    fun interceptorObservesApiKeyAsync() {
        val interceptedRequests = mutableListOf<HttpRequest>()
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(baseUrl)
                .apiKey("sk-test-key")
                .addInterceptor(recordingInterceptor(interceptedRequests))
                .build()

        client.models().retrieve("claude-test").get()

        assertThat(interceptedRequests).hasSize(1)
        assertThat(interceptedRequests[0].headers.values("X-Api-Key"))
            .containsExactly("sk-test-key")
        assertSingleWireHeader("X-Api-Key", "sk-test-key")
    }

    private fun assertSingleWireHeader(name: String, value: String) {
        val wireRequests = findAll(getRequestedFor(urlPathEqualTo("/v1/models/claude-test")))
        assertThat(wireRequests).hasSize(1)
        assertThat(wireRequests[0].header(name).values()).containsExactly(value)
    }

    private fun recordingInterceptor(requests: MutableList<HttpRequest>): Interceptor =
        Interceptor { client ->
            object : HttpClient {
                override fun execute(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): HttpResponse {
                    requests.add(request)
                    return client.execute(request, requestOptions)
                }

                override fun executeAsync(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): CompletableFuture<HttpResponse> {
                    requests.add(request)
                    return client.executeAsync(request, requestOptions)
                }

                override fun close() = client.close()
            }
        }

    companion object {
        private const val MODEL_JSON =
            """{"id":"claude-test","created_at":"2025-02-19T00:00:00Z","display_name":"Claude Test","type":"model"}"""
    }
}
