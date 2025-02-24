// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.CacheControlEphemeral
import com.anthropic.models.CitationCharLocationParam
import com.anthropic.models.MessageCountTokensParams
import com.anthropic.models.MessageCreateParams
import com.anthropic.models.Metadata
import com.anthropic.models.Model
import com.anthropic.models.TextBlockParam
import com.anthropic.models.ToolBash20250124
import com.anthropic.models.ToolChoiceAuto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
class MessageServiceTest {

    @Test
    fun callCreate() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.messages()
        val message =
            messageService.create(
                MessageCreateParams.builder()
                    .maxTokens(1024L)
                    .addUserMessage("Hello, world")
                    .model(Model.CLAUDE_3_7_SONNET_LATEST)
                    .metadata(
                        Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                    )
                    .addStopSequence("string")
                    .systemOfTextBlockParams(
                        listOf(
                            TextBlockParam.builder()
                                .text("Today's date is 2024-06-01.")
                                .cacheControl(CacheControlEphemeral.builder().build())
                                .addCitation(
                                    CitationCharLocationParam.builder()
                                        .citedText("cited_text")
                                        .documentIndex(0L)
                                        .documentTitle("x")
                                        .endCharIndex(0L)
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .build()
                        )
                    )
                    .temperature(1.0)
                    .enabledThinking(1024L)
                    .toolChoice(ToolChoiceAuto.builder().disableParallelToolUse(true).build())
                    .addTool(
                        ToolBash20250124.builder()
                            .cacheControl(CacheControlEphemeral.builder().build())
                            .build()
                    )
                    .topK(5L)
                    .topP(0.7)
                    .build()
            )
        println(message)
        message.validate()
    }

    @Test
    fun callCreateStreaming() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.messages()

        val messageStream =
            messageService.createStreaming(
                MessageCreateParams.builder()
                    .maxTokens(1024L)
                    .addUserMessage("Hello, world")
                    .model(Model.CLAUDE_3_7_SONNET_LATEST)
                    .metadata(
                        Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                    )
                    .addStopSequence("string")
                    .systemOfTextBlockParams(
                        listOf(
                            TextBlockParam.builder()
                                .text("Today's date is 2024-06-01.")
                                .cacheControl(CacheControlEphemeral.builder().build())
                                .addCitation(
                                    CitationCharLocationParam.builder()
                                        .citedText("cited_text")
                                        .documentIndex(0L)
                                        .documentTitle("x")
                                        .endCharIndex(0L)
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .build()
                        )
                    )
                    .temperature(1.0)
                    .enabledThinking(1024L)
                    .toolChoice(ToolChoiceAuto.builder().disableParallelToolUse(true).build())
                    .addTool(
                        ToolBash20250124.builder()
                            .cacheControl(CacheControlEphemeral.builder().build())
                            .build()
                    )
                    .topK(5L)
                    .topP(0.7)
                    .build()
            )

        messageStream.use {
            messageStream.stream().forEach {
                println(it)
                it.validate()
            }
        }
    }

    @Test
    fun callCountTokens() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.messages()
        val messageTokensCount =
            messageService.countTokens(
                MessageCountTokensParams.builder()
                    .addUserMessage("Hello, world")
                    .model(Model.CLAUDE_3_7_SONNET_LATEST)
                    .systemOfTextBlockParams(
                        listOf(
                            TextBlockParam.builder()
                                .text("Today's date is 2024-06-01.")
                                .cacheControl(CacheControlEphemeral.builder().build())
                                .addCitation(
                                    CitationCharLocationParam.builder()
                                        .citedText("cited_text")
                                        .documentIndex(0L)
                                        .documentTitle("x")
                                        .endCharIndex(0L)
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .build()
                        )
                    )
                    .enabledThinking(1024L)
                    .toolChoice(ToolChoiceAuto.builder().disableParallelToolUse(true).build())
                    .addTool(
                        ToolBash20250124.builder()
                            .cacheControl(CacheControlEphemeral.builder().build())
                            .build()
                    )
                    .build()
            )
        println(messageTokensCount)
        messageTokensCount.validate()
    }
}
