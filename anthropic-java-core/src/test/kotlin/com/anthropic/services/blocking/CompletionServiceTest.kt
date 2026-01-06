// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.completions.CompletionCreateParams
import com.anthropic.models.messages.Metadata
import com.anthropic.models.messages.Model
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CompletionServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val completionService = client.completions()

        val completion =
            completionService.create(
                CompletionCreateParams.builder()
                    .addBeta(AnthropicBeta.of("string"))
                    .maxTokensToSample(256L)
                    .model(Model.of("claude-2.1"))
                    .prompt("\n\nHuman: Hello, world!\n\nAssistant:")
                    .metadata(
                        Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                    )
                    .addStopSequence("string")
                    .temperature(1.0)
                    .topK(5L)
                    .topP(0.7)
                    .build()
            )

        completion.validate()
    }

    @Test
    fun createStreaming() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val completionService = client.completions()

        val completionStreamResponse =
            completionService.createStreaming(
                CompletionCreateParams.builder()
                    .addBeta(AnthropicBeta.of("string"))
                    .maxTokensToSample(256L)
                    .model(Model.of("claude-2.1"))
                    .prompt("\n\nHuman: Hello, world!\n\nAssistant:")
                    .metadata(
                        Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                    )
                    .addStopSequence("string")
                    .temperature(1.0)
                    .topK(5L)
                    .topP(0.7)
                    .build()
            )

        completionStreamResponse.use {
            completionStreamResponse.stream().forEach { completion -> completion.validate() }
        }
    }
}
