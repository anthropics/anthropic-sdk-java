// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

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
import com.anthropic.models.beta.messages.MessageCountTokensParams
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.models.messages.Model
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class MessageServiceTest {

    @Disabled("prism validates based on the non-beta endpoint")
    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.beta().messages()

        val betaMessage =
            messageService.create(
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
            )

        betaMessage.validate()
    }

    @Disabled("prism validates based on the non-beta endpoint")
    @Test
    fun createStreaming() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.beta().messages()

        val betaMessageStreamResponse =
            messageService.createStreaming(
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
            )

        betaMessageStreamResponse.use {
            betaMessageStreamResponse.stream().forEach { betaMessage -> betaMessage.validate() }
        }
    }

    @Disabled("prism validates based on the non-beta endpoint")
    @Test
    fun countTokens() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val messageService = client.beta().messages()

        val betaMessageTokensCount =
            messageService.countTokens(
                MessageCountTokensParams.builder()
                    .addBeta(AnthropicBeta.of("string"))
                    .addUserMessage("Hello, world")
                    .model(Model.CLAUDE_SONNET_4_5_20250929)
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
                    .enabledThinking(1024L)
                    .toolChoice(BetaToolChoiceAuto.builder().disableParallelToolUse(true).build())
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
                    .build()
            )

        betaMessageTokensCount.validate()
    }
}
