// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.CompletionCreateParams
import com.anthropic.models.Metadata
import com.anthropic.models.Model
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
class CompletionServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val completionServiceAsync = client.completions()

        val completionFuture =
            completionServiceAsync.create(
                CompletionCreateParams.builder()
                    .maxTokensToSample(256L)
                    .model(Model.CLAUDE_3_7_SONNET_LATEST)
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

        val completion = completionFuture.get()
        completion.validate()
    }

    @Test
    fun createStreaming() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val completionServiceAsync = client.completions()

        val completionStreamResponse =
            completionServiceAsync.createStreaming(
                CompletionCreateParams.builder()
                    .maxTokensToSample(256L)
                    .model(Model.CLAUDE_3_7_SONNET_LATEST)
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

        val onCompleteFuture =
            completionStreamResponse
                .subscribe { completion -> completion.validate() }
                .onCompleteFuture()
        onCompleteFuture.get()
    }
}
