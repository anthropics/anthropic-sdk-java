// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.completions

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.messages.Metadata
import com.anthropic.models.messages.Model
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CompletionCreateParamsTest {

    @Test
    fun create() {
        CompletionCreateParams.builder()
            .addBeta(AnthropicBeta.of("string"))
            .maxTokensToSample(256L)
            .model(Model.of("claude-2.1"))
            .prompt("\n\nHuman: Hello, world!\n\nAssistant:")
            .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
            .addStopSequence("string")
            .temperature(1.0)
            .topK(5L)
            .topP(0.7)
            .build()
    }

    @Test
    fun headers() {
        val params =
            CompletionCreateParams.builder()
                .addBeta(AnthropicBeta.of("string"))
                .maxTokensToSample(256L)
                .model(Model.of("claude-2.1"))
                .prompt("\n\nHuman: Hello, world!\n\nAssistant:")
                .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
                .addStopSequence("string")
                .temperature(1.0)
                .topK(5L)
                .topP(0.7)
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().put("anthropic-beta", "string").build())
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            CompletionCreateParams.builder()
                .maxTokensToSample(256L)
                .model(Model.of("claude-2.1"))
                .prompt("\n\nHuman: Hello, world!\n\nAssistant:")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            CompletionCreateParams.builder()
                .addBeta(AnthropicBeta.of("string"))
                .maxTokensToSample(256L)
                .model(Model.of("claude-2.1"))
                .prompt("\n\nHuman: Hello, world!\n\nAssistant:")
                .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
                .addStopSequence("string")
                .temperature(1.0)
                .topK(5L)
                .topP(0.7)
                .build()

        val body = params._body()

        assertThat(body.maxTokensToSample()).isEqualTo(256L)
        assertThat(body.model()).isEqualTo(Model.of("claude-2.1"))
        assertThat(body.prompt()).isEqualTo("\n\nHuman: Hello, world!\n\nAssistant:")
        assertThat(body.metadata())
            .contains(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
        assertThat(body.stopSequences().getOrNull()).containsExactly("string")
        assertThat(body.temperature()).contains(1.0)
        assertThat(body.topK()).contains(5L)
        assertThat(body.topP()).contains(0.7)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            CompletionCreateParams.builder()
                .maxTokensToSample(256L)
                .model(Model.of("claude-2.1"))
                .prompt("\n\nHuman: Hello, world!\n\nAssistant:")
                .build()

        val body = params._body()

        assertThat(body.maxTokensToSample()).isEqualTo(256L)
        assertThat(body.model()).isEqualTo(Model.of("claude-2.1"))
        assertThat(body.prompt()).isEqualTo("\n\nHuman: Hello, world!\n\nAssistant:")
    }
}
