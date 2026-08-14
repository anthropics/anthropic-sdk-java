// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaModelInfoTest {

    @Test
    fun create() {
        val betaModelInfo =
            BetaModelInfo.builder()
                .id("claude-opus-5")
                .addAllowedFallbackModel("string")
                .capabilities(
                    BetaModelCapabilities.builder()
                        .batch(BetaCapabilitySupport.of(true))
                        .citations(BetaCapabilitySupport.of(true))
                        .codeExecution(BetaCapabilitySupport.of(true))
                        .contextManagement(
                            BetaContextManagementCapability.builder()
                                .clearThinking20251015(BetaCapabilitySupport.of(true))
                                .clearToolUses20250919(BetaCapabilitySupport.of(true))
                                .compact20260112(BetaCapabilitySupport.of(true))
                                .supported(true)
                                .build()
                        )
                        .effort(
                            BetaEffortCapability.builder()
                                .high(BetaCapabilitySupport.of(true))
                                .low(BetaCapabilitySupport.of(true))
                                .max(BetaCapabilitySupport.of(true))
                                .medium(BetaCapabilitySupport.of(true))
                                .supported(true)
                                .xhigh(BetaCapabilitySupport.of(true))
                                .build()
                        )
                        .imageInput(BetaCapabilitySupport.of(true))
                        .pdfInput(BetaCapabilitySupport.of(true))
                        .structuredOutputs(BetaCapabilitySupport.of(true))
                        .thinking(
                            BetaThinkingCapability.builder()
                                .supported(true)
                                .types(
                                    BetaThinkingTypes.builder()
                                        .adaptive(BetaCapabilitySupport.of(true))
                                        .enabled(BetaCapabilitySupport.of(true))
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .createdAt(OffsetDateTime.parse("2026-07-24T00:00:00Z"))
                .displayName("Claude Opus 5")
                .maxInputTokens(0L)
                .maxTokens(0L)
                .build()

        assertThat(betaModelInfo.id()).isEqualTo("claude-opus-5")
        assertThat(betaModelInfo.allowedFallbackModels().getOrNull()).containsExactly("string")
        assertThat(betaModelInfo.capabilities())
            .contains(
                BetaModelCapabilities.builder()
                    .batch(BetaCapabilitySupport.of(true))
                    .citations(BetaCapabilitySupport.of(true))
                    .codeExecution(BetaCapabilitySupport.of(true))
                    .contextManagement(
                        BetaContextManagementCapability.builder()
                            .clearThinking20251015(BetaCapabilitySupport.of(true))
                            .clearToolUses20250919(BetaCapabilitySupport.of(true))
                            .compact20260112(BetaCapabilitySupport.of(true))
                            .supported(true)
                            .build()
                    )
                    .effort(
                        BetaEffortCapability.builder()
                            .high(BetaCapabilitySupport.of(true))
                            .low(BetaCapabilitySupport.of(true))
                            .max(BetaCapabilitySupport.of(true))
                            .medium(BetaCapabilitySupport.of(true))
                            .supported(true)
                            .xhigh(BetaCapabilitySupport.of(true))
                            .build()
                    )
                    .imageInput(BetaCapabilitySupport.of(true))
                    .pdfInput(BetaCapabilitySupport.of(true))
                    .structuredOutputs(BetaCapabilitySupport.of(true))
                    .thinking(
                        BetaThinkingCapability.builder()
                            .supported(true)
                            .types(
                                BetaThinkingTypes.builder()
                                    .adaptive(BetaCapabilitySupport.of(true))
                                    .enabled(BetaCapabilitySupport.of(true))
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
        assertThat(betaModelInfo.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-07-24T00:00:00Z"))
        assertThat(betaModelInfo.displayName()).isEqualTo("Claude Opus 5")
        assertThat(betaModelInfo.maxInputTokens()).contains(0L)
        assertThat(betaModelInfo.maxTokens()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaModelInfo =
            BetaModelInfo.builder()
                .id("claude-opus-5")
                .addAllowedFallbackModel("string")
                .capabilities(
                    BetaModelCapabilities.builder()
                        .batch(BetaCapabilitySupport.of(true))
                        .citations(BetaCapabilitySupport.of(true))
                        .codeExecution(BetaCapabilitySupport.of(true))
                        .contextManagement(
                            BetaContextManagementCapability.builder()
                                .clearThinking20251015(BetaCapabilitySupport.of(true))
                                .clearToolUses20250919(BetaCapabilitySupport.of(true))
                                .compact20260112(BetaCapabilitySupport.of(true))
                                .supported(true)
                                .build()
                        )
                        .effort(
                            BetaEffortCapability.builder()
                                .high(BetaCapabilitySupport.of(true))
                                .low(BetaCapabilitySupport.of(true))
                                .max(BetaCapabilitySupport.of(true))
                                .medium(BetaCapabilitySupport.of(true))
                                .supported(true)
                                .xhigh(BetaCapabilitySupport.of(true))
                                .build()
                        )
                        .imageInput(BetaCapabilitySupport.of(true))
                        .pdfInput(BetaCapabilitySupport.of(true))
                        .structuredOutputs(BetaCapabilitySupport.of(true))
                        .thinking(
                            BetaThinkingCapability.builder()
                                .supported(true)
                                .types(
                                    BetaThinkingTypes.builder()
                                        .adaptive(BetaCapabilitySupport.of(true))
                                        .enabled(BetaCapabilitySupport.of(true))
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .createdAt(OffsetDateTime.parse("2026-07-24T00:00:00Z"))
                .displayName("Claude Opus 5")
                .maxInputTokens(0L)
                .maxTokens(0L)
                .build()

        val roundtrippedBetaModelInfo =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaModelInfo),
                jacksonTypeRef<BetaModelInfo>(),
            )

        assertThat(roundtrippedBetaModelInfo).isEqualTo(betaModelInfo)
    }
}
