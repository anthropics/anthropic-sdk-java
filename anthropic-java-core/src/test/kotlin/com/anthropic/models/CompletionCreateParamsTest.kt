// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CompletionCreateParamsTest {

    @Test
    fun create() {
        CompletionCreateParams.builder()
            .maxTokensToSample(256L)
            .model(Model.CLAUDE_3_7_SONNET_LATEST)
            .prompt("\n\nHuman: Hello, world!\n\nAssistant:")
            .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
            .addStopSequence("string")
            .temperature(1.0)
            .topK(5L)
            .topP(0.7)
            .build()
    }

    @Test
    fun body() {
        val params =
            CompletionCreateParams.builder()
                .maxTokensToSample(256L)
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                .prompt("\n\nHuman: Hello, world!\n\nAssistant:")
                .metadata(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
                .addStopSequence("string")
                .temperature(1.0)
                .topK(5L)
                .topP(0.7)
                .build()

        val body = params._body()

        assertThat(body).isNotNull
        assertThat(body.maxTokensToSample()).isEqualTo(256L)
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_7_SONNET_LATEST)
        assertThat(body.prompt()).isEqualTo("\n\nHuman: Hello, world!\n\nAssistant:")
        assertThat(body.metadata())
            .contains(Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
        assertThat(body.stopSequences()).contains(listOf("string"))
        assertThat(body.temperature()).contains(1.0)
        assertThat(body.topK()).contains(5L)
        assertThat(body.topP()).contains(0.7)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            CompletionCreateParams.builder()
                .maxTokensToSample(256L)
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                .prompt("\n\nHuman: Hello, world!\n\nAssistant:")
                .build()

        val body = params._body()

        assertThat(body).isNotNull
        assertThat(body.maxTokensToSample()).isEqualTo(256L)
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_7_SONNET_LATEST)
        assertThat(body.prompt()).isEqualTo("\n\nHuman: Hello, world!\n\nAssistant:")
    }
}
