// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaToolUnionTest {

    @Test
    fun ofBetaTool() {
        val betaTool =
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
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .description("Get the current weather in a given location")
                .strict(true)
                .type(BetaTool.Type.CUSTOM)
                .build()

        val betaToolUnion = BetaToolUnion.ofBetaTool(betaTool)

        assertThat(betaToolUnion.betaTool()).contains(betaTool)
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250429()).isEmpty
        assertThat(betaToolUnion.textEditor20250728()).isEmpty
        assertThat(betaToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaToolUnion.webFetchTool20250910()).isEmpty
    }

    @Test
    fun ofBetaToolRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
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
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .description("Get the current weather in a given location")
                    .strict(true)
                    .type(BetaTool.Type.CUSTOM)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofBash20241022() {
        val bash20241022 =
            BetaToolBash20241022.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofBash20241022(bash20241022)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.bash20241022()).contains(bash20241022)
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250429()).isEmpty
        assertThat(betaToolUnion.textEditor20250728()).isEmpty
        assertThat(betaToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaToolUnion.webFetchTool20250910()).isEmpty
    }

    @Test
    fun ofBash20241022Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofBash20241022(
                BetaToolBash20241022.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofBash20250124() {
        val bash20250124 =
            BetaToolBash20250124.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofBash20250124(bash20250124)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.bash20250124()).contains(bash20250124)
        assertThat(betaToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250429()).isEmpty
        assertThat(betaToolUnion.textEditor20250728()).isEmpty
        assertThat(betaToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaToolUnion.webFetchTool20250910()).isEmpty
    }

    @Test
    fun ofBash20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofBash20250124(
                BetaToolBash20250124.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofCodeExecutionTool20250522() {
        val codeExecutionTool20250522 =
            BetaCodeExecutionTool20250522.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250522()).contains(codeExecutionTool20250522)
        assertThat(betaToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250429()).isEmpty
        assertThat(betaToolUnion.textEditor20250728()).isEmpty
        assertThat(betaToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaToolUnion.webFetchTool20250910()).isEmpty
    }

    @Test
    fun ofCodeExecutionTool20250522Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofCodeExecutionTool20250522(
                BetaCodeExecutionTool20250522.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofCodeExecutionTool20250825() {
        val codeExecutionTool20250825 =
            BetaCodeExecutionTool20250825.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250825()).contains(codeExecutionTool20250825)
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250429()).isEmpty
        assertThat(betaToolUnion.textEditor20250728()).isEmpty
        assertThat(betaToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaToolUnion.webFetchTool20250910()).isEmpty
    }

    @Test
    fun ofCodeExecutionTool20250825Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofCodeExecutionTool20250825(
                BetaCodeExecutionTool20250825.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofComputerUse20241022() {
        val computerUse20241022 =
            BetaToolComputerUse20241022.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .displayNumber(0L)
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofComputerUse20241022(computerUse20241022)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).contains(computerUse20241022)
        assertThat(betaToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250429()).isEmpty
        assertThat(betaToolUnion.textEditor20250728()).isEmpty
        assertThat(betaToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaToolUnion.webFetchTool20250910()).isEmpty
    }

    @Test
    fun ofComputerUse20241022Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofComputerUse20241022(
                BetaToolComputerUse20241022.builder()
                    .displayHeightPx(1L)
                    .displayWidthPx(1L)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .displayNumber(0L)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofMemoryTool20250818() {
        val memoryTool20250818 =
            BetaMemoryTool20250818.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofMemoryTool20250818(memoryTool20250818)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.memoryTool20250818()).contains(memoryTool20250818)
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250429()).isEmpty
        assertThat(betaToolUnion.textEditor20250728()).isEmpty
        assertThat(betaToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaToolUnion.webFetchTool20250910()).isEmpty
    }

    @Test
    fun ofMemoryTool20250818Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofMemoryTool20250818(
                BetaMemoryTool20250818.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofComputerUse20250124() {
        val computerUse20250124 =
            BetaToolComputerUse20250124.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .displayNumber(0L)
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofComputerUse20250124(computerUse20250124)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).contains(computerUse20250124)
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250429()).isEmpty
        assertThat(betaToolUnion.textEditor20250728()).isEmpty
        assertThat(betaToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaToolUnion.webFetchTool20250910()).isEmpty
    }

    @Test
    fun ofComputerUse20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofComputerUse20250124(
                BetaToolComputerUse20250124.builder()
                    .displayHeightPx(1L)
                    .displayWidthPx(1L)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .displayNumber(0L)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofTextEditor20241022() {
        val textEditor20241022 =
            BetaToolTextEditor20241022.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofTextEditor20241022(textEditor20241022)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).contains(textEditor20241022)
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250429()).isEmpty
        assertThat(betaToolUnion.textEditor20250728()).isEmpty
        assertThat(betaToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaToolUnion.webFetchTool20250910()).isEmpty
    }

    @Test
    fun ofTextEditor20241022Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofTextEditor20241022(
                BetaToolTextEditor20241022.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofTextEditor20250124() {
        val textEditor20250124 =
            BetaToolTextEditor20250124.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofTextEditor20250124(textEditor20250124)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).contains(textEditor20250124)
        assertThat(betaToolUnion.textEditor20250429()).isEmpty
        assertThat(betaToolUnion.textEditor20250728()).isEmpty
        assertThat(betaToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaToolUnion.webFetchTool20250910()).isEmpty
    }

    @Test
    fun ofTextEditor20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofTextEditor20250124(
                BetaToolTextEditor20250124.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofTextEditor20250429() {
        val textEditor20250429 =
            BetaToolTextEditor20250429.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofTextEditor20250429(textEditor20250429)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250429()).contains(textEditor20250429)
        assertThat(betaToolUnion.textEditor20250728()).isEmpty
        assertThat(betaToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaToolUnion.webFetchTool20250910()).isEmpty
    }

    @Test
    fun ofTextEditor20250429Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofTextEditor20250429(
                BetaToolTextEditor20250429.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofTextEditor20250728() {
        val textEditor20250728 =
            BetaToolTextEditor20250728.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .maxCharacters(1L)
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofTextEditor20250728(textEditor20250728)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250429()).isEmpty
        assertThat(betaToolUnion.textEditor20250728()).contains(textEditor20250728)
        assertThat(betaToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaToolUnion.webFetchTool20250910()).isEmpty
    }

    @Test
    fun ofTextEditor20250728Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofTextEditor20250728(
                BetaToolTextEditor20250728.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .maxCharacters(1L)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofWebSearchTool20250305() {
        val webSearchTool20250305 =
            BetaWebSearchTool20250305.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    BetaWebSearchTool20250305.UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val betaToolUnion = BetaToolUnion.ofWebSearchTool20250305(webSearchTool20250305)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250429()).isEmpty
        assertThat(betaToolUnion.textEditor20250728()).isEmpty
        assertThat(betaToolUnion.webSearchTool20250305()).contains(webSearchTool20250305)
        assertThat(betaToolUnion.webFetchTool20250910()).isEmpty
    }

    @Test
    fun ofWebSearchTool20250305Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofWebSearchTool20250305(
                BetaWebSearchTool20250305.builder()
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .maxUses(1L)
                    .strict(true)
                    .userLocation(
                        BetaWebSearchTool20250305.UserLocation.builder()
                            .city("New York")
                            .country("US")
                            .region("California")
                            .timezone("America/New_York")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofWebFetchTool20250910() {
        val webFetchTool20250910 =
            BetaWebFetchTool20250910.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .maxContentTokens(1L)
                .maxUses(1L)
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofWebFetchTool20250910(webFetchTool20250910)

        assertThat(betaToolUnion.betaTool()).isEmpty
        assertThat(betaToolUnion.bash20241022()).isEmpty
        assertThat(betaToolUnion.bash20250124()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(betaToolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(betaToolUnion.computerUse20241022()).isEmpty
        assertThat(betaToolUnion.memoryTool20250818()).isEmpty
        assertThat(betaToolUnion.computerUse20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20241022()).isEmpty
        assertThat(betaToolUnion.textEditor20250124()).isEmpty
        assertThat(betaToolUnion.textEditor20250429()).isEmpty
        assertThat(betaToolUnion.textEditor20250728()).isEmpty
        assertThat(betaToolUnion.webSearchTool20250305()).isEmpty
        assertThat(betaToolUnion.webFetchTool20250910()).contains(webFetchTool20250910)
    }

    @Test
    fun ofWebFetchTool20250910Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofWebFetchTool20250910(
                BetaWebFetchTool20250910.builder()
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                    .maxContentTokens(1L)
                    .maxUses(1L)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
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
        val betaToolUnion =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaToolUnion>())

        val e = assertThrows<AnthropicInvalidDataException> { betaToolUnion.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
