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
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .maxTokens(1024L)
            .addUserMessage("Hello, world")
            .model(Model.CLAUDE_3_7_SONNET_LATEST)
            .container("container")
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
                                JsonValue.from(
                                    mapOf(
                                        "location" to
                                            mapOf(
                                                "description" to
                                                    "The city and state, e.g. San Francisco, CA",
                                                "type" to "string",
                                            ),
                                        "unit" to
                                            mapOf(
                                                "description" to
                                                    "Unit for the output - one of (celsius, fahrenheit)",
                                                "type" to "string",
                                            ),
                                    )
                                )
                            )
                            .addRequired("location")
                            .build()
                    )
                    .name("name")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .description("Get the current weather in a given location")
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
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                .container("container")
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
                                    JsonValue.from(
                                        mapOf(
                                            "location" to
                                                mapOf(
                                                    "description" to
                                                        "The city and state, e.g. San Francisco, CA",
                                                    "type" to "string",
                                                ),
                                            "unit" to
                                                mapOf(
                                                    "description" to
                                                        "Unit for the output - one of (celsius, fahrenheit)",
                                                    "type" to "string",
                                                ),
                                        )
                                    )
                                )
                                .addRequired("location")
                                .build()
                        )
                        .name("name")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .description("Get the current weather in a given location")
                        .type(BetaTool.Type.CUSTOM)
                        .build()
                )
                .topK(5L)
                .topP(0.7)
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
            MessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            MessageCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .maxTokens(1024L)
                .addUserMessage("Hello, world")
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                .container("container")
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
                                    JsonValue.from(
                                        mapOf(
                                            "location" to
                                                mapOf(
                                                    "description" to
                                                        "The city and state, e.g. San Francisco, CA",
                                                    "type" to "string",
                                                ),
                                            "unit" to
                                                mapOf(
                                                    "description" to
                                                        "Unit for the output - one of (celsius, fahrenheit)",
                                                    "type" to "string",
                                                ),
                                        )
                                    )
                                )
                                .addRequired("location")
                                .build()
                        )
                        .name("name")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .description("Get the current weather in a given location")
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
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_7_SONNET_LATEST)
        assertThat(body.container()).contains("container")
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
                                    JsonValue.from(
                                        mapOf(
                                            "location" to
                                                mapOf(
                                                    "description" to
                                                        "The city and state, e.g. San Francisco, CA",
                                                    "type" to "string",
                                                ),
                                            "unit" to
                                                mapOf(
                                                    "description" to
                                                        "Unit for the output - one of (celsius, fahrenheit)",
                                                    "type" to "string",
                                                ),
                                        )
                                    )
                                )
                                .addRequired("location")
                                .build()
                        )
                        .name("name")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .description("Get the current weather in a given location")
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
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
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
        assertThat(body.model()).isEqualTo(Model.CLAUDE_3_7_SONNET_LATEST)
    }
}
