// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.messages

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.CacheControlEphemeral
import com.anthropic.models.MessageBatchCancelParams
import com.anthropic.models.MessageBatchCreateParams
import com.anthropic.models.MessageBatchListParams
import com.anthropic.models.MessageBatchRetrieveParams
import com.anthropic.models.MessageParam
import com.anthropic.models.Metadata
import com.anthropic.models.Model
import com.anthropic.models.TextBlockParam
import com.anthropic.models.Tool
import com.anthropic.models.ToolChoice
import com.anthropic.models.ToolChoiceAuto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
class BatchServiceTest {

    @Test
    fun callCreate() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchService = client.messages().batches()
        val messageBatch =
            batchService.create(
                MessageBatchCreateParams.builder()
                    .requests(
                        listOf(
                            MessageBatchCreateParams.Request.builder()
                                .customId("my-custom-id-1")
                                .params(
                                    MessageBatchCreateParams.Request.Params.builder()
                                        .maxTokens(1024L)
                                        .messages(
                                            listOf(
                                                MessageParam.builder()
                                                    .content(
                                                        MessageParam.Content.ofString(
                                                            "Hello, world"
                                                        )
                                                    )
                                                    .role(MessageParam.Role.USER)
                                                    .build()
                                            )
                                        )
                                        .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                                        .metadata(
                                            Metadata.builder()
                                                .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                                .build()
                                        )
                                        .stopSequences(listOf("string"))
                                        .stream(true)
                                        .system(
                                            MessageBatchCreateParams.Request.Params.System
                                                .ofTextBlockParams(
                                                    listOf(
                                                        TextBlockParam.builder()
                                                            .text("Today's date is 2024-06-01.")
                                                            .type(TextBlockParam.Type.TEXT)
                                                            .cacheControl(
                                                                CacheControlEphemeral.builder()
                                                                    .type(
                                                                        CacheControlEphemeral.Type
                                                                            .EPHEMERAL
                                                                    )
                                                                    .build()
                                                            )
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
                                                    .cacheControl(
                                                        CacheControlEphemeral.builder()
                                                            .type(
                                                                CacheControlEphemeral.Type.EPHEMERAL
                                                            )
                                                            .build()
                                                    )
                                                    .description(
                                                        "Get the current weather in a given location"
                                                    )
                                                    .build()
                                            )
                                        )
                                        .topK(5L)
                                        .topP(0.7)
                                        .build()
                                )
                                .build()
                        )
                    )
                    .build()
            )
        println(messageBatch)
        messageBatch.validate()
    }

    @Test
    fun callRetrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchService = client.messages().batches()
        val messageBatch =
            batchService.retrieve(
                MessageBatchRetrieveParams.builder().messageBatchId("message_batch_id").build()
            )
        println(messageBatch)
        messageBatch.validate()
    }

    @Test
    fun callList() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchService = client.messages().batches()
        val listResponseMessageBatch = batchService.list(MessageBatchListParams.builder().build())
        println(listResponseMessageBatch)
        listResponseMessageBatch.data().forEach { it.validate() }
    }

    @Test
    fun callCancel() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchService = client.messages().batches()
        val messageBatch =
            batchService.cancel(
                MessageBatchCancelParams.builder().messageBatchId("message_batch_id").build()
            )
        println(messageBatch)
        messageBatch.validate()
    }
}
