// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.messages.Model
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageCountTokensParamsTest {

    @Test
    fun create() {
        MessageCountTokensParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .addUserMessage("Hello, world")
            .model(Model.CLAUDE_OPUS_4_6)
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
            .build()
    }

    @Test
    fun headers() {
        val params =
            MessageCountTokensParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_OPUS_4_6)
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
            MessageCountTokensParams.builder()
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_OPUS_4_6)
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            MessageCountTokensParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_OPUS_4_6)
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
                .build()

        val body = params._body()

        assertThat(body.messages())
            .containsExactly(
                BetaMessageParam.builder()
                    .content("Hello, world")
                    .role(BetaMessageParam.Role.USER)
                    .build()
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_OPUS_4_6)
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
        assertThat(body.system())
            .contains(
                MessageCountTokensParams.System.ofBetaTextBlockParams(
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
                MessageCountTokensParams.Tool.ofBeta(
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
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            MessageCountTokensParams.builder()
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_OPUS_4_6)
                .build()

        val body = params._body()

        assertThat(body.messages())
            .containsExactly(
                BetaMessageParam.builder()
                    .content("Hello, world")
                    .role(BetaMessageParam.Role.USER)
                    .build()
            )
        assertThat(body.model()).isEqualTo(Model.CLAUDE_OPUS_4_6)
    }
}
