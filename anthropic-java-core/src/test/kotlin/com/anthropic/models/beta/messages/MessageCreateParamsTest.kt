// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.messages.Model
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageCreateParamsTest {

    @Test
    fun create() {
        MessageCreateParams.builder()
            .addBeta(AnthropicBeta.of("string"))
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
                            .clearAtLeast(BetaInputTokensClearAtLeast.builder().value(0L).build())
                            .clearToolInputs(true)
                            .addExcludeTool("string")
                            .keep(BetaToolUsesKeep.builder().value(0L).build())
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
            .metadata(BetaMetadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
            .outputConfig(
                BetaOutputConfig.builder()
                    .effort(BetaOutputConfig.Effort.LOW)
                    .format(
                        BetaJsonOutputFormat.builder()
                            .schema(
                                BetaJsonOutputFormat.Schema.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
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
            .serviceTier(MessageCreateParams.ServiceTier.AUTO)
            .addStopSequence("string")
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
            .toolChoice(BetaToolChoiceAuto.builder().disableParallelToolUse(true).build())
            .addTool(
                BetaTool.builder()
                    .inputSchema(
                        BetaTool.InputSchema.builder()
                            .properties(
                                BetaTool.InputSchema.Properties.builder()
                                    .putAdditionalProperty("location", JsonValue.from("bar"))
                                    .putAdditionalProperty("unit", JsonValue.from("bar"))
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
    }

    @Test
    fun headers() {
        val params =
            MessageCreateParams.builder()
                .addBeta(AnthropicBeta.of("string"))
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
                                    BetaInputTokensClearAtLeast.builder().value(0L).build()
                                )
                                .clearToolInputs(true)
                                .addExcludeTool("string")
                                .keep(BetaToolUsesKeep.builder().value(0L).build())
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
                    BetaMetadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                )
                .outputConfig(
                    BetaOutputConfig.builder()
                        .effort(BetaOutputConfig.Effort.LOW)
                        .format(
                            BetaJsonOutputFormat.builder()
                                .schema(
                                    BetaJsonOutputFormat.Schema.builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
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
                .serviceTier(MessageCreateParams.ServiceTier.AUTO)
                .addStopSequence("string")
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
                .toolChoice(BetaToolChoiceAuto.builder().disableParallelToolUse(true).build())
                .addTool(
                    BetaTool.builder()
                        .inputSchema(
                            BetaTool.InputSchema.builder()
                                .properties(
                                    BetaTool.InputSchema.Properties.builder()
                                        .putAdditionalProperty("location", JsonValue.from("bar"))
                                        .putAdditionalProperty("unit", JsonValue.from("bar"))
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

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().put("anthropic-beta", "string").build())
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_SONNET_4_5_20250929)
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            MessageCreateParams.builder()
                .addBeta(AnthropicBeta.of("string"))
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
                                    BetaInputTokensClearAtLeast.builder().value(0L).build()
                                )
                                .clearToolInputs(true)
                                .addExcludeTool("string")
                                .keep(BetaToolUsesKeep.builder().value(0L).build())
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
                    BetaMetadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
                )
                .outputConfig(
                    BetaOutputConfig.builder()
                        .effort(BetaOutputConfig.Effort.LOW)
                        .format(
                            BetaJsonOutputFormat.builder()
                                .schema(
                                    BetaJsonOutputFormat.Schema.builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
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
                .serviceTier(MessageCreateParams.ServiceTier.AUTO)
                .addStopSequence("string")
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
                .toolChoice(BetaToolChoiceAuto.builder().disableParallelToolUse(true).build())
                .addTool(
                    BetaTool.builder()
                        .inputSchema(
                            BetaTool.InputSchema.builder()
                                .properties(
                                    BetaTool.InputSchema.Properties.builder()
                                        .putAdditionalProperty("location", JsonValue.from("bar"))
                                        .putAdditionalProperty("unit", JsonValue.from("bar"))
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

        val body = params._body()

        assertThat(body.maxTokens()).isEqualTo(1024L)
        assertThat(body.messages())
            .containsExactly(
                BetaMessageParam.builder()
                    .content("Hello, world")
                    .role(BetaMessageParam.Role.USER)
                    .build()
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_SONNET_4_5_20250929)
        assertThat(body.container())
            .contains(
                MessageCreateParams.Container.ofBetaContainerParams(
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
            )
        assertThat(body.contextManagement())
            .contains(
                BetaContextManagementConfig.builder()
                    .addEdit(
                        BetaClearToolUses20250919Edit.builder()
                            .clearAtLeast(BetaInputTokensClearAtLeast.builder().value(0L).build())
                            .clearToolInputs(true)
                            .addExcludeTool("string")
                            .keep(BetaToolUsesKeep.builder().value(0L).build())
                            .inputTokensTrigger(1L)
                            .build()
                    )
                    .build()
            )
        assertThat(body.mcpServers().getOrNull())
            .containsExactly(
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
        assertThat(body.metadata())
            .contains(BetaMetadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build())
        assertThat(body.outputConfig())
            .contains(
                BetaOutputConfig.builder()
                    .effort(BetaOutputConfig.Effort.LOW)
                    .format(
                        BetaJsonOutputFormat.builder()
                            .schema(
                                BetaJsonOutputFormat.Schema.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
        assertThat(body.outputFormat())
            .contains(
                BetaJsonOutputFormat.builder()
                    .schema(
                        BetaJsonOutputFormat.Schema.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .build()
            )
        assertThat(body.serviceTier()).contains(MessageCreateParams.ServiceTier.AUTO)
        assertThat(body.stopSequences().getOrNull()).containsExactly("string")
        assertThat(body.system())
            .contains(
                MessageCreateParams.System.ofBetaTextBlockParams(
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
            )
        assertThat(body.temperature()).contains(1.0)
        assertThat(body.thinking())
            .contains(
                BetaThinkingConfigParam.ofEnabled(
                    BetaThinkingConfigEnabled.builder().budgetTokens(1024L).build()
                )
            )
        assertThat(body.toolChoice())
            .contains(
                BetaToolChoice.ofAuto(
                    BetaToolChoiceAuto.builder().disableParallelToolUse(true).build()
                )
            )
        assertThat(body.tools().getOrNull())
            .containsExactly(
                BetaToolUnion.ofBetaTool(
                    BetaTool.builder()
                        .inputSchema(
                            BetaTool.InputSchema.builder()
                                .properties(
                                    BetaTool.InputSchema.Properties.builder()
                                        .putAdditionalProperty("location", JsonValue.from("bar"))
                                        .putAdditionalProperty("unit", JsonValue.from("bar"))
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
                        .addInputExample(
                            BetaTool.InputExample.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .strict(true)
                        .type(BetaTool.Type.CUSTOM)
                        .build()
                )
            )
        assertThat(body.topK()).contains(5L)
        assertThat(body.topP()).contains(0.7)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_SONNET_4_5_20250929)
                .build()

        val body = params._body()

        assertThat(body.maxTokens()).isEqualTo(1024L)
        assertThat(body.messages())
            .containsExactly(
                BetaMessageParam.builder()
                    .content("Hello, world")
                    .role(BetaMessageParam.Role.USER)
                    .build()
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_SONNET_4_5_20250929)
    }
}
