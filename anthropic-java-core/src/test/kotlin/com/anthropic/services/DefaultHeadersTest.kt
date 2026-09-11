package com.anthropic.services

import com.anthropic.client.AnthropicClient
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.getPackageVersion
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.Model
import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.findAll
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class DefaultHeadersTest {

    private lateinit var client: AnthropicClient

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        client =
            AnthropicOkHttpClient.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("my-anthropic-api-key")
                .build()
    }

    @Test
    fun createHeaders() {
        val messageService = client.messages()
        stubFor(post(anyUrl()).willReturn(ok("{}")))

        messageService.create(
            MessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_OPUS_5)
                .build()
        )

        val request = findAll(postRequestedFor(anyUrl())).single()
        assertThat(request.header("Accept").values()).containsExactly("application/json")
        assertThat(request.header("User-Agent").values())
            .containsExactly("AnthropicClientImpl/Java ${getPackageVersion()}")
    }

    @Test
    fun rawResponseCreateHeaders() {
        val messageService = client.withRawResponse().messages()
        stubFor(post(anyUrl()).willReturn(ok("{}")))

        messageService.create(
            MessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_OPUS_5)
                .build()
        )

        val request = findAll(postRequestedFor(anyUrl())).single()
        assertThat(request.header("Accept").values()).containsExactly("application/json")
        assertThat(request.header("User-Agent").values())
            .containsExactly("AnthropicClientImpl/Java ${getPackageVersion()}")
    }

    @Test
    fun createHeadersAcceptSetWithOptions() {
        val messageService = client.withOptions { it.putHeader("Accept", "*/*") }.messages()
        stubFor(post(anyUrl()).willReturn(ok("{}")))

        messageService.create(
            MessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_OPUS_5)
                .build()
        )

        val request = findAll(postRequestedFor(anyUrl())).single()
        assertThat(request.header("Accept").values()).containsExactly("*/*")
        assertThat(request.header("User-Agent").values())
            .containsExactly("AnthropicClientImpl/Java ${getPackageVersion()}")
    }

    @Test
    fun rawResponseCreateHeadersUserAgentSetWithOptions() {
        val messageService =
            client
                .withRawResponse()
                .withOptions { it.putHeader("User-Agent", "my-app/1.0") }
                .messages()
        stubFor(post(anyUrl()).willReturn(ok("{}")))

        messageService.create(
            MessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_OPUS_5)
                .build()
        )

        val request = findAll(postRequestedFor(anyUrl())).single()
        assertThat(request.header("Accept").values()).containsExactly("application/json")
        assertThat(request.header("User-Agent").values()).containsExactly("my-app/1.0")
    }

    @Test
    fun createStreamingHeaders() {
        val messageService = client.messages()
        stubFor(post(anyUrl()).willReturn(ok("{}")))

        messageService
            .createStreaming(
                MessageCreateParams.builder()
                    .maxTokens(1024L)
                    .addUserMessage("Hello, world")
                    .model(Model.CLAUDE_OPUS_5)
                    .build()
            )
            .close()

        val request = findAll(postRequestedFor(anyUrl())).single()
        assertThat(request.header("Accept").values()).containsExactly("text/event-stream")
        assertThat(request.header("User-Agent").values())
            .containsExactly("AnthropicClientImpl/Java ${getPackageVersion()}")
    }
}
