// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
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
import com.anthropic.models.messages.Model
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BatchCreateParamsTest {

    @Test
    fun create() {
        BatchCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .addRequest(
                BatchCreateParams.Request.builder()
                    .customId("my-custom-id-1")
                    .params(
                        BatchCreateParams.Request.Params.builder()
                            .maxTokens(1024L)
                            .addUserMessage("Hello, world")
                            .model(Model.CLAUDE_OPUS_4_6)
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
                                            .keep(BetaToolUsesKeep.builder().value(0L).build())
                                            .inputTokensTrigger(1L)
                                            .build()
                                    )
                                    .build()
                            )
                            .inferenceGeo("inference_geo")
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
                                    .format(
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
                                    .build()
                            )
                            .outputFormat(
                                BetaJsonOutputFormat.builder()
                                    .schema(
                                        BetaJsonOutputFormat.Schema.builder()
                                            .putAdditionalProperty("foo", JsonValue.from("bar"))
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
                                BetaToolChoiceAuto.builder().disableParallelToolUse(true).build()
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
                                    .description("Get the current weather in a given location")
                                    .eagerInputStreaming(true)
                                    .addInputExample(
                                        BetaTool.InputExample.builder()
                                            .putAdditionalProperty("foo", JsonValue.from("bar"))
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
    }

    @Test
    fun headers() {
        val params =
            BatchCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .addRequest(
                    BatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            BatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addUserMessage("Hello, world")
                                .model(Model.CLAUDE_OPUS_4_6)
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
                                                .keep(BetaToolUsesKeep.builder().value(0L).build())
                                                .inputTokensTrigger(1L)
                                                .build()
                                        )
                                        .build()
                                )
                                .inferenceGeo("inference_geo")
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
                                        .format(
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
                                        .build()
                                )
                                .outputFormat(
                                    BetaJsonOutputFormat.builder()
                                        .schema(
                                            BetaJsonOutputFormat.Schema.builder()
                                                .putAdditionalProperty("foo", JsonValue.from("bar"))
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
                                        .description("Get the current weather in a given location")
                                        .eagerInputStreaming(true)
                                        .addInputExample(
                                            BetaTool.InputExample.builder()
                                                .putAdditionalProperty("foo", JsonValue.from("bar"))
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

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            BatchCreateParams.builder()
                .addRequest(
                    BatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            BatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addUserMessage("Hello, world")
                                .model(Model.CLAUDE_OPUS_4_6)
                                .build()
                        )
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            BatchCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .addRequest(
                    BatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            BatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addUserMessage("Hello, world")
                                .model(Model.CLAUDE_OPUS_4_6)
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
                                                .keep(BetaToolUsesKeep.builder().value(0L).build())
                                                .inputTokensTrigger(1L)
                                                .build()
                                        )
                                        .build()
                                )
                                .inferenceGeo("inference_geo")
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
                                        .format(
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
                                        .build()
                                )
                                .outputFormat(
                                    BetaJsonOutputFormat.builder()
                                        .schema(
                                            BetaJsonOutputFormat.Schema.builder()
                                                .putAdditionalProperty("foo", JsonValue.from("bar"))
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
                                        .description("Get the current weather in a given location")
                                        .eagerInputStreaming(true)
                                        .addInputExample(
                                            BetaTool.InputExample.builder()
                                                .putAdditionalProperty("foo", JsonValue.from("bar"))
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

        val body = params._body()

        assertThat(body.requests())
            .containsExactly(
                BatchCreateParams.Request.builder()
                    .customId("my-custom-id-1")
                    .params(
                        BatchCreateParams.Request.Params.builder()
                            .maxTokens(1024L)
                            .addUserMessage("Hello, world")
                            .model(Model.CLAUDE_OPUS_4_6)
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
                                            .keep(BetaToolUsesKeep.builder().value(0L).build())
                                            .inputTokensTrigger(1L)
                                            .build()
                                    )
                                    .build()
                            )
                            .inferenceGeo("inference_geo")
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
                                    .format(
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
                                    .build()
                            )
                            .outputFormat(
                                BetaJsonOutputFormat.builder()
                                    .schema(
                                        BetaJsonOutputFormat.Schema.builder()
                                            .putAdditionalProperty("foo", JsonValue.from("bar"))
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
                                BetaToolChoiceAuto.builder().disableParallelToolUse(true).build()
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
                                    .description("Get the current weather in a given location")
                                    .eagerInputStreaming(true)
                                    .addInputExample(
                                        BetaTool.InputExample.builder()
                                            .putAdditionalProperty("foo", JsonValue.from("bar"))
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
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            BatchCreateParams.builder()
                .addRequest(
                    BatchCreateParams.Request.builder()
                        .customId("my-custom-id-1")
                        .params(
                            BatchCreateParams.Request.Params.builder()
                                .maxTokens(1024L)
                                .addUserMessage("Hello, world")
                                .model(Model.CLAUDE_OPUS_4_6)
                                .build()
                        )
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.requests())
            .containsExactly(
                BatchCreateParams.Request.builder()
                    .customId("my-custom-id-1")
                    .params(
                        BatchCreateParams.Request.Params.builder()
                            .maxTokens(1024L)
                            .addUserMessage("Hello, world")
                            .model(Model.CLAUDE_OPUS_4_6)
                            .build()
                    )
                    .build()
            )
    }
}
