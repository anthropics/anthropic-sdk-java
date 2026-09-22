package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaResponseToolUnionTest {

    @Test
    fun ofBetaResponseTool() {
        val betaResponseTool =
            BetaResponseTool.builder()
                .inputSchema(
                    BetaResponseToolInputSchema.builder()
                        .properties(
                            BetaResponseToolInputSchema.Properties.builder()
                                .putAdditionalProperty("location", JsonValue.from("bar"))
                                .putAdditionalProperty("unit", JsonValue.from("bar"))
                                .build()
                        )
                        .addRequired("location")
                        .build()
                )
                .name("name")
                .addAllowedCaller(BetaResponseTool.AllowedCaller.DIRECT)
                .deferLoading(true)
                .description("Get the current weather in a given location")
                .eagerInputStreaming(true)
                .addInputExample(
                    BetaResponseTool.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .type(BetaResponseTool.Type.CUSTOM)
                .build()

        val betaResponseToolUnion = BetaResponseToolUnion.ofBetaResponseTool(betaResponseTool)

        assertThat(betaResponseToolUnion.betaResponseTool()).contains(betaResponseTool)
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofBetaResponseToolRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofBetaResponseTool(
                BetaResponseTool.builder()
                    .inputSchema(
                        BetaResponseToolInputSchema.builder()
                            .properties(
                                BetaResponseToolInputSchema.Properties.builder()
                                    .putAdditionalProperty("location", JsonValue.from("bar"))
                                    .putAdditionalProperty("unit", JsonValue.from("bar"))
                                    .build()
                            )
                            .addRequired("location")
                            .build()
                    )
                    .name("name")
                    .addAllowedCaller(BetaResponseTool.AllowedCaller.DIRECT)
                    .deferLoading(true)
                    .description("Get the current weather in a given location")
                    .eagerInputStreaming(true)
                    .addInputExample(
                        BetaResponseTool.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .type(BetaResponseTool.Type.CUSTOM)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofToolBash20241022() {
        val toolBash20241022 =
            BetaToolBash20241022.builder()
                .addAllowedCaller(BetaToolBash20241022.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolBash20241022.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaResponseToolUnion = BetaResponseToolUnion.ofToolBash20241022(toolBash20241022)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).contains(toolBash20241022)
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofToolBash20241022Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolBash20241022(
                BetaToolBash20241022.builder()
                    .addAllowedCaller(BetaToolBash20241022.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaToolBash20241022.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofToolBash20250124() {
        val toolBash20250124 =
            BetaToolBash20250124.builder()
                .addAllowedCaller(BetaToolBash20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolBash20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaResponseToolUnion = BetaResponseToolUnion.ofToolBash20250124(toolBash20250124)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).contains(toolBash20250124)
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofToolBash20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolBash20250124(
                BetaToolBash20250124.builder()
                    .addAllowedCaller(BetaToolBash20250124.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaToolBash20250124.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofCodeExecutionTool20250522() {
        val codeExecutionTool20250522 =
            BetaCodeExecutionTool20250522.builder()
                .addAllowedCaller(BetaCodeExecutionTool20250522.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522())
            .contains(codeExecutionTool20250522)
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofCodeExecutionTool20250522Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofCodeExecutionTool20250522(
                BetaCodeExecutionTool20250522.builder()
                    .addAllowedCaller(BetaCodeExecutionTool20250522.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofCodeExecutionTool20250825() {
        val codeExecutionTool20250825 =
            BetaCodeExecutionTool20250825.builder()
                .addAllowedCaller(BetaCodeExecutionTool20250825.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825())
            .contains(codeExecutionTool20250825)
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofCodeExecutionTool20250825Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofCodeExecutionTool20250825(
                BetaCodeExecutionTool20250825.builder()
                    .addAllowedCaller(BetaCodeExecutionTool20250825.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofCodeExecutionTool20260120() {
        val codeExecutionTool20260120 =
            BetaCodeExecutionTool20260120.builder()
                .addAllowedCaller(BetaCodeExecutionTool20260120.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120())
            .contains(codeExecutionTool20260120)
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofCodeExecutionTool20260120Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofCodeExecutionTool20260120(
                BetaCodeExecutionTool20260120.builder()
                    .addAllowedCaller(BetaCodeExecutionTool20260120.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofCodeExecutionTool20260521() {
        val codeExecutionTool20260521 =
            BetaCodeExecutionTool20260521.builder()
                .addAllowedCaller(BetaCodeExecutionTool20260521.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofCodeExecutionTool20260521(codeExecutionTool20260521)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521())
            .contains(codeExecutionTool20260521)
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofCodeExecutionTool20260521Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofCodeExecutionTool20260521(
                BetaCodeExecutionTool20260521.builder()
                    .addAllowedCaller(BetaCodeExecutionTool20260521.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofBrowserToolset20260801() {
        val browserToolset20260801 =
            BetaBrowserToolset20260801.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .configs(
                    BetaBrowserToolsetConfigs.builder()
                        .closeTab(
                            BetaBrowserCloseTabConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .doubleClick(
                            BetaBrowserDoubleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .fileUpload(
                            BetaBrowserFileUploadConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .find(
                            BetaBrowserFindConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .formInput(
                            BetaBrowserFormInputConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .getPageText(
                            BetaBrowserGetPageTextConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .holdKey(
                            BetaBrowserHoldKeyConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .hover(
                            BetaBrowserHoverConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .javascriptExec(
                            BetaBrowserJavascriptExecConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .key(
                            BetaBrowserKeyConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .leftClick(
                            BetaBrowserLeftClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftClickDrag(
                            BetaBrowserLeftClickDragConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftMouseDown(
                            BetaBrowserLeftMouseDownConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftMouseUp(
                            BetaBrowserLeftMouseUpConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .listTabs(
                            BetaBrowserListTabsConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .middleClick(
                            BetaBrowserMiddleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .mouseMove(
                            BetaBrowserMouseMoveConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .navigate(
                            BetaBrowserNavigateConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .newTab(
                            BetaBrowserNewTabConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .readConsole(
                            BetaBrowserReadConsoleConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .readNetwork(
                            BetaBrowserReadNetworkConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .readPage(
                            BetaBrowserReadPageConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .rightClick(
                            BetaBrowserRightClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .screenshot(
                            BetaBrowserScreenshotConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .scroll(
                            BetaBrowserScrollConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .scrollTo(
                            BetaBrowserScrollToConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .switchTab(
                            BetaBrowserSwitchTabConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .tripleClick(
                            BetaBrowserTripleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .type(
                            BetaBrowserTypeConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .wait(
                            BetaBrowserWaitConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .zoom(
                            BetaBrowserZoomConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .build()
                )
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofBrowserToolset20260801(browserToolset20260801)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).contains(browserToolset20260801)
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofBrowserToolset20260801Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofBrowserToolset20260801(
                BetaBrowserToolset20260801.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .configs(
                        BetaBrowserToolsetConfigs.builder()
                            .closeTab(
                                BetaBrowserCloseTabConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .doubleClick(
                                BetaBrowserDoubleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .fileUpload(
                                BetaBrowserFileUploadConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .find(
                                BetaBrowserFindConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .formInput(
                                BetaBrowserFormInputConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .getPageText(
                                BetaBrowserGetPageTextConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .holdKey(
                                BetaBrowserHoldKeyConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .hover(
                                BetaBrowserHoverConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .javascriptExec(
                                BetaBrowserJavascriptExecConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .key(
                                BetaBrowserKeyConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftClick(
                                BetaBrowserLeftClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftClickDrag(
                                BetaBrowserLeftClickDragConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftMouseDown(
                                BetaBrowserLeftMouseDownConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftMouseUp(
                                BetaBrowserLeftMouseUpConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .listTabs(
                                BetaBrowserListTabsConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .middleClick(
                                BetaBrowserMiddleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .mouseMove(
                                BetaBrowserMouseMoveConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .navigate(
                                BetaBrowserNavigateConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .newTab(
                                BetaBrowserNewTabConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .readConsole(
                                BetaBrowserReadConsoleConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .readNetwork(
                                BetaBrowserReadNetworkConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .readPage(
                                BetaBrowserReadPageConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .rightClick(
                                BetaBrowserRightClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .screenshot(
                                BetaBrowserScreenshotConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .scroll(
                                BetaBrowserScrollConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .scrollTo(
                                BetaBrowserScrollToConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .switchTab(
                                BetaBrowserSwitchTabConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .tripleClick(
                                BetaBrowserTripleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .type(
                                BetaBrowserTypeConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .wait(
                                BetaBrowserWaitConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .zoom(
                                BetaBrowserZoomConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofToolComputerUse20241022() {
        val toolComputerUse20241022 =
            BetaToolComputerUse20241022.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .addAllowedCaller(BetaToolComputerUse20241022.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .displayNumber(0L)
                .addInputExample(
                    BetaToolComputerUse20241022.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolComputerUse20241022(toolComputerUse20241022)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022())
            .contains(toolComputerUse20241022)
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofToolComputerUse20241022Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolComputerUse20241022(
                BetaToolComputerUse20241022.builder()
                    .displayHeightPx(1L)
                    .displayWidthPx(1L)
                    .addAllowedCaller(BetaToolComputerUse20241022.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .displayNumber(0L)
                    .addInputExample(
                        BetaToolComputerUse20241022.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofMemoryTool20250818() {
        val memoryTool20250818 =
            BetaMemoryTool20250818.builder()
                .addAllowedCaller(BetaMemoryTool20250818.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaMemoryTool20250818.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaResponseToolUnion = BetaResponseToolUnion.ofMemoryTool20250818(memoryTool20250818)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).contains(memoryTool20250818)
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofMemoryTool20250818Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofMemoryTool20250818(
                BetaMemoryTool20250818.builder()
                    .addAllowedCaller(BetaMemoryTool20250818.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaMemoryTool20250818.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofToolComputerUse20250124() {
        val toolComputerUse20250124 =
            BetaToolComputerUse20250124.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .addAllowedCaller(BetaToolComputerUse20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .displayNumber(0L)
                .addInputExample(
                    BetaToolComputerUse20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolComputerUse20250124(toolComputerUse20250124)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124())
            .contains(toolComputerUse20250124)
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofToolComputerUse20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolComputerUse20250124(
                BetaToolComputerUse20250124.builder()
                    .displayHeightPx(1L)
                    .displayWidthPx(1L)
                    .addAllowedCaller(BetaToolComputerUse20250124.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .displayNumber(0L)
                    .addInputExample(
                        BetaToolComputerUse20250124.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofToolTextEditor20241022() {
        val toolTextEditor20241022 =
            BetaToolTextEditor20241022.builder()
                .addAllowedCaller(BetaToolTextEditor20241022.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20241022.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolTextEditor20241022(toolTextEditor20241022)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).contains(toolTextEditor20241022)
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofToolTextEditor20241022Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolTextEditor20241022(
                BetaToolTextEditor20241022.builder()
                    .addAllowedCaller(BetaToolTextEditor20241022.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaToolTextEditor20241022.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofToolComputerUse20251124() {
        val toolComputerUse20251124 =
            BetaToolComputerUse20251124.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .addAllowedCaller(BetaToolComputerUse20251124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .displayNumber(0L)
                .enableZoom(true)
                .addInputExample(
                    BetaToolComputerUse20251124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolComputerUse20251124(toolComputerUse20251124)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124())
            .contains(toolComputerUse20251124)
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofToolComputerUse20251124Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolComputerUse20251124(
                BetaToolComputerUse20251124.builder()
                    .displayHeightPx(1L)
                    .displayWidthPx(1L)
                    .addAllowedCaller(BetaToolComputerUse20251124.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .displayNumber(0L)
                    .enableZoom(true)
                    .addInputExample(
                        BetaToolComputerUse20251124.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofComputerToolset20260801() {
        val computerToolset20260801 =
            BetaComputerToolset20260801.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .configs(
                    BetaComputerToolsetConfigs.builder()
                        .cursorPosition(
                            BetaComputerCursorPositionConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .doubleClick(
                            BetaComputerDoubleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .holdKey(
                            BetaComputerHoldKeyConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .key(
                            BetaComputerKeyConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .leftClick(
                            BetaComputerLeftClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftClickDrag(
                            BetaComputerLeftClickDragConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftMouseDown(
                            BetaComputerLeftMouseDownConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftMouseUp(
                            BetaComputerLeftMouseUpConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .middleClick(
                            BetaComputerMiddleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .mouseMove(
                            BetaComputerMouseMoveConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .rightClick(
                            BetaComputerRightClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .screenshot(
                            BetaComputerScreenshotConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .scroll(
                            BetaComputerScrollConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .tripleClick(
                            BetaComputerTripleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .type(
                            BetaComputerTypeConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .wait(
                            BetaComputerWaitConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .zoom(
                            BetaComputerZoomConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .build()
                )
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofComputerToolset20260801(computerToolset20260801)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801())
            .contains(computerToolset20260801)
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofComputerToolset20260801Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofComputerToolset20260801(
                BetaComputerToolset20260801.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .configs(
                        BetaComputerToolsetConfigs.builder()
                            .cursorPosition(
                                BetaComputerCursorPositionConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .doubleClick(
                                BetaComputerDoubleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .holdKey(
                                BetaComputerHoldKeyConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .key(
                                BetaComputerKeyConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftClick(
                                BetaComputerLeftClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftClickDrag(
                                BetaComputerLeftClickDragConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftMouseDown(
                                BetaComputerLeftMouseDownConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftMouseUp(
                                BetaComputerLeftMouseUpConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .middleClick(
                                BetaComputerMiddleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .mouseMove(
                                BetaComputerMouseMoveConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .rightClick(
                                BetaComputerRightClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .screenshot(
                                BetaComputerScreenshotConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .scroll(
                                BetaComputerScrollConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .tripleClick(
                                BetaComputerTripleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .type(
                                BetaComputerTypeConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .wait(
                                BetaComputerWaitConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .zoom(
                                BetaComputerZoomConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofToolTextEditor20250124() {
        val toolTextEditor20250124 =
            BetaToolTextEditor20250124.builder()
                .addAllowedCaller(BetaToolTextEditor20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolTextEditor20250124(toolTextEditor20250124)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).contains(toolTextEditor20250124)
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofToolTextEditor20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolTextEditor20250124(
                BetaToolTextEditor20250124.builder()
                    .addAllowedCaller(BetaToolTextEditor20250124.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaToolTextEditor20250124.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofToolTextEditor20250429() {
        val toolTextEditor20250429 =
            BetaToolTextEditor20250429.builder()
                .addAllowedCaller(BetaToolTextEditor20250429.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20250429.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolTextEditor20250429(toolTextEditor20250429)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).contains(toolTextEditor20250429)
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofToolTextEditor20250429Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolTextEditor20250429(
                BetaToolTextEditor20250429.builder()
                    .addAllowedCaller(BetaToolTextEditor20250429.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaToolTextEditor20250429.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofToolTextEditor20250728() {
        val toolTextEditor20250728 =
            BetaToolTextEditor20250728.builder()
                .addAllowedCaller(BetaToolTextEditor20250728.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20250728.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .maxCharacters(1L)
                .strict(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolTextEditor20250728(toolTextEditor20250728)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).contains(toolTextEditor20250728)
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofToolTextEditor20250728Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolTextEditor20250728(
                BetaToolTextEditor20250728.builder()
                    .addAllowedCaller(BetaToolTextEditor20250728.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaToolTextEditor20250728.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .maxCharacters(1L)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofWebSearchTool20250305() {
        val webSearchTool20250305 =
            BetaWebSearchTool20250305.builder()
                .addAllowedCaller(BetaWebSearchTool20250305.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    BetaUserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebSearchTool20250305(webSearchTool20250305)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).contains(webSearchTool20250305)
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofWebSearchTool20250305Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebSearchTool20250305(
                BetaWebSearchTool20250305.builder()
                    .addAllowedCaller(BetaWebSearchTool20250305.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .maxUses(1L)
                    .strict(true)
                    .userLocation(
                        BetaUserLocation.builder()
                            .city("New York")
                            .country("US")
                            .region("California")
                            .timezone("America/New_York")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofWebFetchTool20250910() {
        val webFetchTool20250910 =
            BetaWebFetchTool20250910.builder()
                .addAllowedCaller(BetaWebFetchTool20250910.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .strict(true)
                .urlSources(
                    BetaWebFetchUrlSources.builder()
                        .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .userInput(BetaWebFetchUrlSourceAll.builder().build())
                        .build()
                )
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebFetchTool20250910(webFetchTool20250910)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).contains(webFetchTool20250910)
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofWebFetchTool20250910Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebFetchTool20250910(
                BetaWebFetchTool20250910.builder()
                    .addAllowedCaller(BetaWebFetchTool20250910.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                    .deferLoading(true)
                    .maxContentTokens(1L)
                    .maxUses(1L)
                    .strict(true)
                    .urlSources(
                        BetaWebFetchUrlSources.builder()
                            .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .userInput(BetaWebFetchUrlSourceAll.builder().build())
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofWebSearchTool20260209() {
        val webSearchTool20260209 =
            BetaWebSearchTool20260209.builder()
                .addAllowedCaller(BetaWebSearchTool20260209.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    BetaUserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebSearchTool20260209(webSearchTool20260209)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).contains(webSearchTool20260209)
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofWebSearchTool20260209Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebSearchTool20260209(
                BetaWebSearchTool20260209.builder()
                    .addAllowedCaller(BetaWebSearchTool20260209.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .maxUses(1L)
                    .strict(true)
                    .userLocation(
                        BetaUserLocation.builder()
                            .city("New York")
                            .country("US")
                            .region("California")
                            .timezone("America/New_York")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofWebFetchTool20260209() {
        val webFetchTool20260209 =
            BetaWebFetchTool20260209.builder()
                .addAllowedCaller(BetaWebFetchTool20260209.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .strict(true)
                .urlSources(
                    BetaWebFetchUrlSources.builder()
                        .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .userInput(BetaWebFetchUrlSourceAll.builder().build())
                        .build()
                )
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebFetchTool20260209(webFetchTool20260209)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).contains(webFetchTool20260209)
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofWebFetchTool20260209Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebFetchTool20260209(
                BetaWebFetchTool20260209.builder()
                    .addAllowedCaller(BetaWebFetchTool20260209.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                    .deferLoading(true)
                    .maxContentTokens(1L)
                    .maxUses(1L)
                    .strict(true)
                    .urlSources(
                        BetaWebFetchUrlSources.builder()
                            .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .userInput(BetaWebFetchUrlSourceAll.builder().build())
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofWebFetchTool20260309() {
        val webFetchTool20260309 =
            BetaWebFetchTool20260309.builder()
                .addAllowedCaller(BetaWebFetchTool20260309.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .strict(true)
                .urlSources(
                    BetaWebFetchUrlSources.builder()
                        .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .userInput(BetaWebFetchUrlSourceAll.builder().build())
                        .build()
                )
                .useCache(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebFetchTool20260309(webFetchTool20260309)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).contains(webFetchTool20260309)
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofWebFetchTool20260309Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebFetchTool20260309(
                BetaWebFetchTool20260309.builder()
                    .addAllowedCaller(BetaWebFetchTool20260309.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                    .deferLoading(true)
                    .maxContentTokens(1L)
                    .maxUses(1L)
                    .strict(true)
                    .urlSources(
                        BetaWebFetchUrlSources.builder()
                            .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .userInput(BetaWebFetchUrlSourceAll.builder().build())
                            .build()
                    )
                    .useCache(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofWebSearchTool20260318() {
        val webSearchTool20260318 =
            BetaWebSearchTool20260318.builder()
                .addAllowedCaller(BetaWebSearchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .responseInclusion(BetaWebSearchTool20260318.ResponseInclusion.FULL)
                .strict(true)
                .userLocation(
                    BetaUserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebSearchTool20260318(webSearchTool20260318)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).contains(webSearchTool20260318)
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofWebSearchTool20260318Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebSearchTool20260318(
                BetaWebSearchTool20260318.builder()
                    .addAllowedCaller(BetaWebSearchTool20260318.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .maxUses(1L)
                    .responseInclusion(BetaWebSearchTool20260318.ResponseInclusion.FULL)
                    .strict(true)
                    .userLocation(
                        BetaUserLocation.builder()
                            .city("New York")
                            .country("US")
                            .region("California")
                            .timezone("America/New_York")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofWebFetchTool20260318() {
        val webFetchTool20260318 =
            BetaWebFetchTool20260318.builder()
                .addAllowedCaller(BetaWebFetchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .responseInclusion(BetaWebFetchTool20260318.ResponseInclusion.FULL)
                .strict(true)
                .urlSources(
                    BetaWebFetchUrlSources.builder()
                        .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .userInput(BetaWebFetchUrlSourceAll.builder().build())
                        .build()
                )
                .useCache(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebFetchTool20260318(webFetchTool20260318)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).contains(webFetchTool20260318)
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofWebFetchTool20260318Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofWebFetchTool20260318(
                BetaWebFetchTool20260318.builder()
                    .addAllowedCaller(BetaWebFetchTool20260318.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                    .deferLoading(true)
                    .maxContentTokens(1L)
                    .maxUses(1L)
                    .responseInclusion(BetaWebFetchTool20260318.ResponseInclusion.FULL)
                    .strict(true)
                    .urlSources(
                        BetaWebFetchUrlSources.builder()
                            .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .userInput(BetaWebFetchUrlSourceAll.builder().build())
                            .build()
                    )
                    .useCache(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofAdvisorTool20260301() {
        val advisorTool20260301 =
            BetaAdvisorTool20260301.builder()
                .model(Model.CLAUDE_FABLE_5_1)
                .addAllowedCaller(BetaAdvisorTool20260301.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .caching(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxTokens(1024L)
                .maxUses(1L)
                .strict(true)
                .build()

        val betaResponseToolUnion = BetaResponseToolUnion.ofAdvisorTool20260301(advisorTool20260301)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).contains(advisorTool20260301)
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofAdvisorTool20260301Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofAdvisorTool20260301(
                BetaAdvisorTool20260301.builder()
                    .model(Model.CLAUDE_FABLE_5_1)
                    .addAllowedCaller(BetaAdvisorTool20260301.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .caching(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .maxTokens(1024L)
                    .maxUses(1L)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofToolSearchToolBm25_20251119() {
        val toolSearchToolBm25_20251119 =
            BetaToolSearchToolBm25_20251119.builder()
                .type(BetaToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
                .addAllowedCaller(BetaToolSearchToolBm25_20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolSearchToolBm25_20251119(toolSearchToolBm25_20251119)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119())
            .contains(toolSearchToolBm25_20251119)
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofToolSearchToolBm25_20251119Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolSearchToolBm25_20251119(
                BetaToolSearchToolBm25_20251119.builder()
                    .type(BetaToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
                    .addAllowedCaller(BetaToolSearchToolBm25_20251119.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofToolSearchToolRegex20251119() {
        val toolSearchToolRegex20251119 =
            BetaToolSearchToolRegex20251119.builder()
                .type(BetaToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
                .addAllowedCaller(BetaToolSearchToolRegex20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolSearchToolRegex20251119(toolSearchToolRegex20251119)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119())
            .contains(toolSearchToolRegex20251119)
        assertThat(betaResponseToolUnion.mcpToolset()).isEmpty
    }

    @Test
    fun ofToolSearchToolRegex20251119Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofToolSearchToolRegex20251119(
                BetaToolSearchToolRegex20251119.builder()
                    .type(BetaToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
                    .addAllowedCaller(BetaToolSearchToolRegex20251119.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    @Test
    fun ofMcpToolset() {
        val mcpToolset =
            BetaMcpToolset.builder()
                .mcpServerName("x")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .configs(
                    BetaMcpToolset.Configs.builder()
                        .putAdditionalProperty(
                            "foo",
                            JsonValue.from(mapOf("defer_loading" to true, "enabled" to true)),
                        )
                        .build()
                )
                .defaultConfig(
                    BetaMcpToolDefaultConfig.builder().deferLoading(true).enabled(true).build()
                )
                .addTool(
                    BetaMcpToolParam.builder()
                        .inputSchema(
                            BetaMcpToolParam.InputSchema.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .name("x")
                        .description("description")
                        .build()
                )
                .build()

        val betaResponseToolUnion = BetaResponseToolUnion.ofMcpToolset(mcpToolset)

        assertThat(betaResponseToolUnion.betaResponseTool()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolBash20250124()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(betaResponseToolUnion.codeExecutionTool20260521()).isEmpty
        assertThat(betaResponseToolUnion.browserToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20241022()).isEmpty
        assertThat(betaResponseToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20241022()).isEmpty
        assertThat(betaResponseToolUnion.toolComputerUse20251124()).isEmpty
        assertThat(betaResponseToolUnion.computerToolset20260801()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250124()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250429()).isEmpty
        assertThat(betaResponseToolUnion.toolTextEditor20250728()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20250910()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260209()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260309()).isEmpty
        assertThat(betaResponseToolUnion.webSearchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.webFetchTool20260318()).isEmpty
        assertThat(betaResponseToolUnion.advisorTool20260301()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolBm25_20251119()).isEmpty
        assertThat(betaResponseToolUnion.toolSearchToolRegex20251119()).isEmpty
        assertThat(betaResponseToolUnion.mcpToolset()).contains(mcpToolset)
    }

    @Test
    fun ofMcpToolsetRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolUnion =
            BetaResponseToolUnion.ofMcpToolset(
                BetaMcpToolset.builder()
                    .mcpServerName("x")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .configs(
                        BetaMcpToolset.Configs.builder()
                            .putAdditionalProperty(
                                "foo",
                                JsonValue.from(mapOf("defer_loading" to true, "enabled" to true)),
                            )
                            .build()
                    )
                    .defaultConfig(
                        BetaMcpToolDefaultConfig.builder().deferLoading(true).enabled(true).build()
                    )
                    .addTool(
                        BetaMcpToolParam.builder()
                            .inputSchema(
                                BetaMcpToolParam.InputSchema.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .name("x")
                            .description("description")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaResponseToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolUnion),
                jacksonTypeRef<BetaResponseToolUnion>(),
            )

        assertThat(roundtrippedBetaResponseToolUnion).isEqualTo(betaResponseToolUnion)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaResponseToolUnion =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaResponseToolUnion>())

        val e = assertThrows<AnthropicInvalidDataException> { betaResponseToolUnion.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaResponseToolUnion.deferLoading()).isEmpty
        assertThat(betaResponseToolUnion.strict()).isEmpty
        assertThat(betaResponseToolUnion.cacheControl()).isEmpty
        assertThat(betaResponseToolUnion.displayHeightPx()).isEmpty
        assertThat(betaResponseToolUnion.displayWidthPx()).isEmpty
        assertThat(betaResponseToolUnion.displayNumber()).isEmpty
        assertThat(betaResponseToolUnion.allowedDomains()).isEmpty
        assertThat(betaResponseToolUnion.blockedDomains()).isEmpty
        assertThat(betaResponseToolUnion.maxUses()).isEmpty
        assertThat(betaResponseToolUnion.userLocation()).isEmpty
        assertThat(betaResponseToolUnion.citations()).isEmpty
        assertThat(betaResponseToolUnion.maxContentTokens()).isEmpty
        assertThat(betaResponseToolUnion.urlSources()).isEmpty
        assertThat(betaResponseToolUnion.useCache()).isEmpty
    }
}
