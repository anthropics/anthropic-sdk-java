package com.anthropic.client.okhttp

import com.anthropic.core.LogLevel
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.Interceptor
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
@ResourceLock(Resources.SYSTEM_ERR)
internal class BaseUrlE2ETest {

    private lateinit var baseUrl: String
    private lateinit var originalErr: PrintStream
    private lateinit var errContent: ByteArrayOutputStream

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        baseUrl = wmRuntimeInfo.httpBaseUrl
        stubFor(get(urlPathEqualTo("/v1/models/claude-test")).willReturn(ok().withBody(MODEL_JSON)))
        originalErr = System.err
        errContent = ByteArrayOutputStream()
        System.setErr(PrintStream(errContent))
    }

    @AfterEach
    fun afterEach() {
        System.setErr(originalErr)
    }

    @Test
    fun logIncludesBaseUrl() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(baseUrl)
                .apiKey("sk-test-key")
                .logLevel(LogLevel.INFO)
                .build()

        client.models().retrieve("claude-test")

        assertThat(stderrOutput()).startsWith("--> GET $baseUrl/v1/models/claude-test\n")
    }

    @Test
    fun logIncludesBaseUrlAsync() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(baseUrl)
                .apiKey("sk-test-key")
                .logLevel(LogLevel.INFO)
                .build()

        client.models().retrieve("claude-test").get()

        assertThat(stderrOutput()).startsWith("--> GET $baseUrl/v1/models/claude-test\n")
    }

    @Test
    fun interceptorObservesDefaultBaseUrl() {
        val interceptedRequests = mutableListOf<HttpRequest>()
        val client =
            AnthropicOkHttpClient.builder()
                .apiKey("sk-test-key")
                .addInterceptor(shortCircuitingInterceptor(interceptedRequests))
                .build()

        client.models().retrieve("claude-test")

        assertThat(interceptedRequests).hasSize(1)
        assertThat(interceptedRequests[0].url())
            .isEqualTo("https://api.anthropic.com/v1/models/claude-test")
    }

    private fun stderrOutput(): String = errContent.toString("UTF-8")

    private fun shortCircuitingInterceptor(requests: MutableList<HttpRequest>): Interceptor =
        Interceptor { _ ->
            object : HttpClient {
                override fun execute(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): HttpResponse {
                    requests.add(request)
                    return okResponse()
                }

                override fun executeAsync(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): CompletableFuture<HttpResponse> =
                    CompletableFuture.completedFuture(execute(request, requestOptions))

                override fun close() {}
            }
        }

    private fun okResponse(): HttpResponse =
        object : HttpResponse {
            override fun statusCode() = 200

            override fun headers() = Headers.builder().build()

            override fun body() = MODEL_JSON.byteInputStream()

            override fun close() {}
        }

    companion object {
        private const val MODEL_JSON =
            """{"id":"claude-test","created_at":"2025-02-19T00:00:00Z","display_name":"Claude Test","type":"model"}"""
    }
}
