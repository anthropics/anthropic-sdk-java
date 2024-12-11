// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.*
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
                    .messages(
                        listOf(
                            MessageParam.builder()
                                .content(MessageParam.Content.ofString("string"))
                                .role(MessageParam.Role.USER)
                                .build()
                        )
                    )
                    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                    .metadata(
                        Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                    )
                    .stopSequences(listOf("string"))
                    .system(
                        MessageCreateParams.System.ofTextBlockParams(
                            listOf(
                                TextBlockParam.builder()
                                    .text("Today's date is 2024-06-01.")
                                    .type(TextBlockParam.Type.TEXT)
                                    .build()
                            )
                        )
                    )
                    .temperature(1.0)
                    .toolChoice(
                        ToolChoice.ofToolChoiceAuto(
                            ToolChoiceAuto.builder()
                                .type(ToolChoiceAuto.Type.AUTO)
                                .disableParallelToolUse(true)
                                .build()
                        )
                    )
                    .tools(
                        listOf(
                            Tool.builder()
                                .inputSchema(
                                    Tool.InputSchema.builder()
                                        .type(Tool.InputSchema.Type.OBJECT)
                                        .properties(
                                            JsonValue.from(
                                                mapOf(
                                                    "location" to
                                                        mapOf(
                                                            "description" to
                                                                "The city and state, e.g. San Francisco, CA",
                                                            "type" to "string"
                                                        ),
                                                    "unit" to
                                                        mapOf(
                                                            "description" to
                                                                "Unit for the output - one of (celsius, fahrenheit)",
                                                            "type" to "string"
                                                        )
                                                )
                                            )
                                        )
                                        .build()
                                )
                                .name("x")
                                .description("Get the current weather in a given location")
                                .build()
                        )
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
                    .messages(
                        listOf(
                            MessageParam.builder()
                                .content(MessageParam.Content.ofString("string"))
                                .role(MessageParam.Role.USER)
                                .build()
                        )
                    )
                    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                    .metadata(
                        Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                    )
                    .stopSequences(listOf("string"))
                    .system(
                        MessageCreateParams.System.ofTextBlockParams(
                            listOf(
                                TextBlockParam.builder()
                                    .text("Today's date is 2024-06-01.")
                                    .type(TextBlockParam.Type.TEXT)
                                    .build()
                            )
                        )
                    )
                    .temperature(1.0)
                    .toolChoice(
                        ToolChoice.ofToolChoiceAuto(
                            ToolChoiceAuto.builder()
                                .type(ToolChoiceAuto.Type.AUTO)
                                .disableParallelToolUse(true)
                                .build()
                        )
                    )
                    .tools(
                        listOf(
                            Tool.builder()
                                .inputSchema(
                                    Tool.InputSchema.builder()
                                        .type(Tool.InputSchema.Type.OBJECT)
                                        .properties(
                                            JsonValue.from(
                                                mapOf(
                                                    "location" to
                                                        mapOf(
                                                            "description" to
                                                                "The city and state, e.g. San Francisco, CA",
                                                            "type" to "string"
                                                        ),
                                                    "unit" to
                                                        mapOf(
                                                            "description" to
                                                                "Unit for the output - one of (celsius, fahrenheit)",
                                                            "type" to "string"
                                                        )
                                                )
                                            )
                                        )
                                        .build()
                                )
                                .name("x")
                                .description("Get the current weather in a given location")
                                .build()
                        )
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
}
