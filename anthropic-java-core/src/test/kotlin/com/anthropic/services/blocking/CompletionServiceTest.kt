// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.CompletionCreateParams
import com.anthropic.models.Metadata
import com.anthropic.models.Model
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
class CompletionServiceTest {

    @Test
    fun callCreate() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val completionService = client.completions()
        val completion =
            completionService.create(
                CompletionCreateParams.builder()
                    .maxTokensToSample(256L)
                    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                    .prompt("\n\nHuman: Hello, world!\n\nAssistant:")
                    .metadata(
                        Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                    )
                    .stopSequences(listOf("string"))
                    .temperature(1.0)
                    .topK(5L)
                    .topP(0.7)
                    .build()
            )
        println(completion)
        completion.validate()
    }

    @Test
    fun callCreateStreaming() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val completionService = client.completions()

        val completionStream =
            completionService.createStreaming(
                CompletionCreateParams.builder()
                    .maxTokensToSample(256L)
                    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                    .prompt("\n\nHuman: Hello, world!\n\nAssistant:")
                    .metadata(
                        Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                    )
                    .stopSequences(listOf("string"))
                    .temperature(1.0)
                    .topK(5L)
                    .topP(0.7)
                    .build()
            )

        completionStream.use {
            completionStream.stream().forEach {
                println(it)
                it.validate()
            }
        }
    }
}
