// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.messages

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.messages.BetaCacheControlEphemeral
import com.anthropic.models.beta.messages.BetaCitationCharLocationParam
import com.anthropic.models.beta.messages.BetaClearToolUses20250919Edit
import com.anthropic.models.beta.messages.BetaContainerParams
import com.anthropic.models.beta.messages.BetaContextManagementConfig
import com.anthropic.models.beta.messages.BetaInputTokensClearAtLeast
import com.anthropic.models.beta.messages.BetaJsonOutputFormat
import com.anthropic.models.beta.messages.BetaMetadata
import com.anthropic.models.beta.messages.BetaOutputConfig
import com.anthropic.models.beta.messages.BetaRequestMcpServerToolConfiguration
import com.anthropic.models.beta.messages.BetaRequestMcpServerUrlDefinition
import com.anthropic.models.beta.messages.BetaSkillParams
import com.anthropic.models.beta.messages.BetaTextBlockParam
import com.anthropic.models.beta.messages.BetaTool
import com.anthropic.models.beta.messages.BetaToolChoiceAuto
import com.anthropic.models.beta.messages.BetaToolUsesKeep
import com.anthropic.models.beta.messages.batches.BatchCancelParams
import com.anthropic.models.beta.messages.batches.BatchCreateParams
import com.anthropic.models.beta.messages.batches.BatchDeleteParams
import com.anthropic.models.beta.messages.batches.BatchResultsParams
import com.anthropic.models.beta.messages.batches.BatchRetrieveParams
import com.anthropic.models.messages.Model
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class BatchServiceTest {

    @Disabled("prism validates based on the non-beta endpoint")
    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchService = client.beta().messages().batches()

        val betaMessageBatch =
            batchService.create(
                BatchCreateParams.builder()
                    .addBeta(AnthropicBeta.of("string"))
                    .addRequest(
                        BatchCreateParams.Request.builder()
                            .customId("my-custom-id-1")
                            .params(
                                BatchCreateParams.Request.Params.builder()
                                    .maxTokens(1024L)
                                    .addUserMessage("Hello, world")
                                    .model(Model.CLAUDE_SONNET_4_5_20250929)
                                    .container(
                                        BetaContainerParams.builder()
                                            .id("id")
                                            .addSkill(
                                                BetaSkillParams.builder()
                                                    .skillId("x")
                                                    .type(BetaSkillParams.Type.ANTHROPIC)
                                                    .version("x")
                                                    .build()
                                            )
                                            .build()
                                    )
                                    .contextManagement(
                                        BetaContextManagementConfig.builder()
                                            .addEdit(
                                                BetaClearToolUses20250919Edit.builder()
                                                    .clearAtLeast(
                                                        BetaInputTokensClearAtLeast.builder()
                                                            .value(0L)
                                                            .build()
                                                    )
                                                    .clearToolInputs(true)
                                                    .addExcludeTool("string")
                                                    .keep(
                                                        BetaToolUsesKeep.builder().value(0L).build()
                                                    )
                                                    .inputTokensTrigger(1L)
                                                    .build()
                                            )
                                            .build()
                                    )
                                    .addMcpServer(
                                        BetaRequestMcpServerUrlDefinition.builder()
                                            .name("name")
                                            .url("url")
                                            .authorizationToken("authorization_token")
                                            .toolConfiguration(
                                                BetaRequestMcpServerToolConfiguration.builder()
                                                    .addAllowedTool("string")
                                                    .enabled(true)
                                                    .build()
                                            )
                                            .build()
                                    )
                                    .metadata(
                                        BetaMetadata.builder()
                                            .userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
                                            .build()
                                    )
                                    .outputConfig(
                                        BetaOutputConfig.builder()
                                            .effort(BetaOutputConfig.Effort.LOW)
                                            .build()
                                    )
                                    .outputFormat(
                                        BetaJsonOutputFormat.builder()
                                            .schema(
                                                BetaJsonOutputFormat.Schema.builder()
                                                    .putAdditionalProperty(
                                                        "foo",
                                                        JsonValue.from("bar"),
                                                    )
                                                    .build()
                                            )
                                            .build()
                                    )
                                    .serviceTier(BatchCreateParams.Request.Params.ServiceTier.AUTO)
                                    .addStopSequence("string")
                                    .stream(true)
                                    .systemOfBetaTextBlockParams(
                                        listOf(
                                            BetaTextBlockParam.builder()
                                                .text("Today's date is 2024-06-01.")
                                                .cacheControl(
                                                    BetaCacheControlEphemeral.builder()
                                                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                                        .build()
                                                )
                                                .addCitation(
                                                    BetaCitationCharLocationParam.builder()
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
                                    .toolChoice(
                                        BetaToolChoiceAuto.builder()
                                            .disableParallelToolUse(true)
                                            .build()
                                    )
                                    .addTool(
                                        BetaTool.builder()
                                            .inputSchema(
                                                BetaTool.InputSchema.builder()
                                                    .properties(
                                                        BetaTool.InputSchema.Properties.builder()
                                                            .putAdditionalProperty(
                                                                "location",
                                                                JsonValue.from("bar"),
                                                            )
                                                            .putAdditionalProperty(
                                                                "unit",
                                                                JsonValue.from("bar"),
                                                            )
                                                            .build()
                                                    )
                                                    .addRequired("location")
                                                    .build()
                                            )
                                            .name("name")
                                            .addAllowedCaller(BetaTool.AllowedCaller.DIRECT)
                                            .cacheControl(
                                                BetaCacheControlEphemeral.builder()
                                                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                                    .build()
                                            )
                                            .deferLoading(true)
                                            .description(
                                                "Get the current weather in a given location"
                                            )
                                            .addInputExample(
                                                BetaTool.InputExample.builder()
                                                    .putAdditionalProperty(
                                                        "foo",
                                                        JsonValue.from("bar"),
                                                    )
                                                    .build()
                                            )
                                            .strict(true)
                                            .type(BetaTool.Type.CUSTOM)
                                            .build()
                                    )
                                    .topK(5L)
                                    .topP(0.7)
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        betaMessageBatch.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchService = client.beta().messages().batches()

        val betaMessageBatch =
            batchService.retrieve(
                BatchRetrieveParams.builder()
                    .messageBatchId("message_batch_id")
                    .addBeta(AnthropicBeta.of("string"))
                    .build()
            )

        betaMessageBatch.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchService = client.beta().messages().batches()

        val page = batchService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchService = client.beta().messages().batches()

        val betaDeletedMessageBatch =
            batchService.delete(
                BatchDeleteParams.builder()
                    .messageBatchId("message_batch_id")
                    .addBeta(AnthropicBeta.of("string"))
                    .build()
            )

        betaDeletedMessageBatch.validate()
    }

    @Test
    fun cancel() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchService = client.beta().messages().batches()

        val betaMessageBatch =
            batchService.cancel(
                BatchCancelParams.builder()
                    .messageBatchId("message_batch_id")
                    .addBeta(AnthropicBeta.of("string"))
                    .build()
            )

        betaMessageBatch.validate()
    }

    @Disabled("Prism doesn't support application/x-jsonl responses")
    @Test
    fun resultsStreaming() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val batchService = client.beta().messages().batches()

        val betaMessageBatchIndividualResponseStreamResponse =
            batchService.resultsStreaming(
                BatchResultsParams.builder()
                    .messageBatchId("message_batch_id")
                    .addBeta(AnthropicBeta.of("string"))
                    .build()
            )

        betaMessageBatchIndividualResponseStreamResponse.use {
            betaMessageBatchIndividualResponseStreamResponse.stream().forEach {
                betaMessageBatchIndividualResponse ->
                betaMessageBatchIndividualResponse.validate()
            }
        }
    }
}
