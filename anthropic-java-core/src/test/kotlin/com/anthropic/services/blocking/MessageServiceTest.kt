// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.messages.CacheControlEphemeral
import com.anthropic.models.messages.CitationCharLocationParam
import com.anthropic.models.messages.JsonOutputFormat
import com.anthropic.models.messages.MessageCountTokensParams
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.Metadata
import com.anthropic.models.messages.Model
import com.anthropic.models.messages.OutputConfig
import com.anthropic.models.messages.TextBlockParam
import com.anthropic.models.messages.Tool
import com.anthropic.models.messages.ToolChoiceAuto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class MessageServiceTest {

    @Test
    fun create() {
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
                    .model(Model.CLAUDE_OPUS_4_6)
                    .container("container")
                    .inferenceGeo("inference_geo")
                    .metadata(
                        Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                    )
                    .outputConfig(
                        OutputConfig.builder()
                            .effort(OutputConfig.Effort.LOW)
                            .format(
                                JsonOutputFormat.builder()
                                    .schema(
                                        JsonOutputFormat.Schema.builder()
                                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                                            .build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .serviceTier(MessageCreateParams.ServiceTier.AUTO)
                    .addStopSequence("string")
                    .systemOfTextBlockParams(
                        listOf(
                            TextBlockParam.builder()
                                .text("Today's date is 2024-06-01.")
                                .cacheControl(
                                    CacheControlEphemeral.builder()
                                        .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
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
                        Tool.builder()
                            .inputSchema(
                                Tool.InputSchema.builder()
                                    .properties(
                                        Tool.InputSchema.Properties.builder()
                                            .putAdditionalProperty(
                                                "location",
                                                JsonValue.from("bar"),
                                            )
                                            .putAdditionalProperty("unit", JsonValue.from("bar"))
                                            .build()
                                    )
                                    .addRequired("location")
                                    .build()
                            )
                            .name("name")
                            .addAllowedCaller(Tool.AllowedCaller.DIRECT)
                            .cacheControl(
                                CacheControlEphemeral.builder()
                                    .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                    .build()
                            )
                            .deferLoading(true)
                            .description("Get the current weather in a given location")
                            .eagerInputStreaming(true)
                            .addInputExample(
                                Tool.InputExample.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .strict(true)
                            .type(Tool.Type.CUSTOM)
                            .build()
                    )
                    .topK(5L)
                    .topP(0.7)
                    .build()
            )

        message.validate()
    }

    @Test
    fun createStreaming() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.messages()

        val messageStreamResponse =
            messageService.createStreaming(
                MessageCreateParams.builder()
                    .maxTokens(1024L)
                    .addUserMessage("Hello, world")
                    .model(Model.CLAUDE_OPUS_4_6)
                    .container("container")
                    .inferenceGeo("inference_geo")
                    .metadata(
                        Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                    )
                    .outputConfig(
                        OutputConfig.builder()
                            .effort(OutputConfig.Effort.LOW)
                            .format(
                                JsonOutputFormat.builder()
                                    .schema(
                                        JsonOutputFormat.Schema.builder()
                                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                                            .build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .serviceTier(MessageCreateParams.ServiceTier.AUTO)
                    .addStopSequence("string")
                    .systemOfTextBlockParams(
                        listOf(
                            TextBlockParam.builder()
                                .text("Today's date is 2024-06-01.")
                                .cacheControl(
                                    CacheControlEphemeral.builder()
                                        .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
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
                        Tool.builder()
                            .inputSchema(
                                Tool.InputSchema.builder()
                                    .properties(
                                        Tool.InputSchema.Properties.builder()
                                            .putAdditionalProperty(
                                                "location",
                                                JsonValue.from("bar"),
                                            )
                                            .putAdditionalProperty("unit", JsonValue.from("bar"))
                                            .build()
                                    )
                                    .addRequired("location")
                                    .build()
                            )
                            .name("name")
                            .addAllowedCaller(Tool.AllowedCaller.DIRECT)
                            .cacheControl(
                                CacheControlEphemeral.builder()
                                    .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                    .build()
                            )
                            .deferLoading(true)
                            .description("Get the current weather in a given location")
                            .eagerInputStreaming(true)
                            .addInputExample(
                                Tool.InputExample.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .strict(true)
                            .type(Tool.Type.CUSTOM)
                            .build()
                    )
                    .topK(5L)
                    .topP(0.7)
                    .build()
            )

        messageStreamResponse.use {
            messageStreamResponse.stream().forEach { message -> message.validate() }
        }
    }

    @Test
    fun countTokens() {
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
                    .model(Model.CLAUDE_OPUS_4_6)
                    .outputConfig(
                        OutputConfig.builder()
                            .effort(OutputConfig.Effort.LOW)
                            .format(
                                JsonOutputFormat.builder()
                                    .schema(
                                        JsonOutputFormat.Schema.builder()
                                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                                            .build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .systemOfTextBlockParams(
                        listOf(
                            TextBlockParam.builder()
                                .text("Today's date is 2024-06-01.")
                                .cacheControl(
                                    CacheControlEphemeral.builder()
                                        .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
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
                        Tool.builder()
                            .inputSchema(
                                Tool.InputSchema.builder()
                                    .properties(
                                        Tool.InputSchema.Properties.builder()
                                            .putAdditionalProperty(
                                                "location",
                                                JsonValue.from("bar"),
                                            )
                                            .putAdditionalProperty("unit", JsonValue.from("bar"))
                                            .build()
                                    )
                                    .addRequired("location")
                                    .build()
                            )
                            .name("name")
                            .addAllowedCaller(Tool.AllowedCaller.DIRECT)
                            .cacheControl(
                                CacheControlEphemeral.builder()
                                    .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                    .build()
                            )
                            .deferLoading(true)
                            .description("Get the current weather in a given location")
                            .eagerInputStreaming(true)
                            .addInputExample(
                                Tool.InputExample.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .strict(true)
                            .type(Tool.Type.CUSTOM)
                            .build()
                    )
                    .build()
            )

        messageTokensCount.validate()
    }
}
