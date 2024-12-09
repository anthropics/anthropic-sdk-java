// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.messages

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonNull
import com.anthropic.models.*
import com.anthropic.models.BetaMessageBatchListParams
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
        val batchService = client.beta().messages().batches()
        val betaMessageBatch =
            batchService.create(
                BetaMessageBatchCreateParams.builder()
                    .requests(
                        listOf(
                            BetaMessageBatchCreateParams.Request.builder()
                                .customId("my-custom-id-1")
                                .params(
                                    BetaMessageBatchCreateParams.Request.Params.builder()
                                        .maxTokens(1024L)
                                        .messages(
                                            listOf(
                                                BetaMessageParam.builder()
                                                    .content(
                                                        BetaMessageParam.Content.ofString("string")
                                                    )
                                                    .role(BetaMessageParam.Role.USER)
                                                    .build()
                                            )
                                        )
                                        .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                                        .metadata(
                                            BetaMetadata.builder()
                                                .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                                .build()
                                        )
                                        .stopSequences(listOf("string"))
                                        .stream(true)
                                        .system(
                                            BetaMessageBatchCreateParams.Request.Params.System
                                                .ofString("string")
                                        )
                                        .temperature(1.0)
                                        .toolChoice(
                                            BetaToolChoice.ofBetaToolChoiceAuto(
                                                BetaToolChoiceAuto.builder()
                                                    .type(BetaToolChoiceAuto.Type.AUTO)
                                                    .disableParallelToolUse(true)
                                                    .build()
                                            )
                                        )
                                        .tools(
                                            listOf(
                                                BetaToolUnion.ofBetaTool(
                                                    BetaTool.builder()
                                                        .inputSchema(
                                                            BetaTool.InputSchema.builder()
                                                                .type(
                                                                    BetaTool.InputSchema.Type.OBJECT
                                                                )
                                                                .properties(JsonNull.of())
                                                                .build()
                                                        )
                                                        .name("x")
                                                        .cacheControl(
                                                            BetaCacheControlEphemeral.builder()
                                                                .type(
                                                                    BetaCacheControlEphemeral.Type
                                                                        .EPHEMERAL
                                                                )
                                                                .build()
                                                        )
                                                        .description(
                                                            "Get the current weather in a given location"
                                                        )
                                                        .type(BetaTool.Type.CUSTOM)
                                                        .build()
                                                )
                                            )
                                        )
                                        .topK(5L)
                                        .topP(0.7)
                                        .build()
                                )
                                .build()
                        )
                    )
                    .betas(listOf(AnthropicBeta.MESSAGE_BATCHES_2024_09_24))
                    .build()
            )
        println(betaMessageBatch)
        betaMessageBatch.validate()
    }

    @Test
    fun callRetrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchService = client.beta().messages().batches()
        val betaMessageBatch =
            batchService.retrieve(
                BetaMessageBatchRetrieveParams.builder()
                    .messageBatchId("message_batch_id")
                    .betas(listOf(AnthropicBeta.MESSAGE_BATCHES_2024_09_24))
                    .build()
            )
        println(betaMessageBatch)
        betaMessageBatch.validate()
    }

    @Test
    fun callList() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchService = client.beta().messages().batches()
        val betaListResponseMessageBatch =
            batchService.list(BetaMessageBatchListParams.builder().build())
        println(betaListResponseMessageBatch)
        betaListResponseMessageBatch.data().forEach { it.validate() }
    }

    @Test
    fun callCancel() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchService = client.beta().messages().batches()
        val betaMessageBatch =
            batchService.cancel(
                BetaMessageBatchCancelParams.builder()
                    .messageBatchId("message_batch_id")
                    .betas(listOf(AnthropicBeta.MESSAGE_BATCHES_2024_09_24))
                    .build()
            )
        println(betaMessageBatch)
        betaMessageBatch.validate()
    }
}
