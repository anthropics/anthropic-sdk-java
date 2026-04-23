// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaModelInfoTest {

    @Test
    fun create() {
        val betaModelInfo =
            BetaModelInfo.builder()
                .id("claude-opus-4-6")
                .capabilities(
                    BetaModelCapabilities.builder()
                        .batch(BetaCapabilitySupport.builder().supported(true).build())
                        .citations(BetaCapabilitySupport.builder().supported(true).build())
                        .codeExecution(BetaCapabilitySupport.builder().supported(true).build())
                        .contextManagement(
                            BetaContextManagementCapability.builder()
                                .clearThinking20251015(
                                    BetaCapabilitySupport.builder().supported(true).build()
                                )
                                .clearToolUses20250919(
                                    BetaCapabilitySupport.builder().supported(true).build()
                                )
                                .compact20260112(
                                    BetaCapabilitySupport.builder().supported(true).build()
                                )
                                .supported(true)
                                .build()
                        )
                        .effort(
                            BetaEffortCapability.builder()
                                .high(BetaCapabilitySupport.builder().supported(true).build())
                                .low(BetaCapabilitySupport.builder().supported(true).build())
                                .max(BetaCapabilitySupport.builder().supported(true).build())
                                .medium(BetaCapabilitySupport.builder().supported(true).build())
                                .supported(true)
                                .xhigh(BetaCapabilitySupport.builder().supported(true).build())
                                .build()
                        )
                        .imageInput(BetaCapabilitySupport.builder().supported(true).build())
                        .pdfInput(BetaCapabilitySupport.builder().supported(true).build())
                        .structuredOutputs(BetaCapabilitySupport.builder().supported(true).build())
                        .thinking(
                            BetaThinkingCapability.builder()
                                .supported(true)
                                .types(
                                    BetaThinkingTypes.builder()
                                        .adaptive(
                                            BetaCapabilitySupport.builder().supported(true).build()
                                        )
                                        .enabled(
                                            BetaCapabilitySupport.builder().supported(true).build()
                                        )
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .createdAt(OffsetDateTime.parse("2026-02-04T00:00:00Z"))
                .displayName("Claude Opus 4.6")
                .maxInputTokens(0L)
                .maxTokens(0L)
                .build()

        assertThat(betaModelInfo.id()).isEqualTo("claude-opus-4-6")
        assertThat(betaModelInfo.capabilities())
            .contains(
                BetaModelCapabilities.builder()
                    .batch(BetaCapabilitySupport.builder().supported(true).build())
                    .citations(BetaCapabilitySupport.builder().supported(true).build())
                    .codeExecution(BetaCapabilitySupport.builder().supported(true).build())
                    .contextManagement(
                        BetaContextManagementCapability.builder()
                            .clearThinking20251015(
                                BetaCapabilitySupport.builder().supported(true).build()
                            )
                            .clearToolUses20250919(
                                BetaCapabilitySupport.builder().supported(true).build()
                            )
                            .compact20260112(
                                BetaCapabilitySupport.builder().supported(true).build()
                            )
                            .supported(true)
                            .build()
                    )
                    .effort(
                        BetaEffortCapability.builder()
                            .high(BetaCapabilitySupport.builder().supported(true).build())
                            .low(BetaCapabilitySupport.builder().supported(true).build())
                            .max(BetaCapabilitySupport.builder().supported(true).build())
                            .medium(BetaCapabilitySupport.builder().supported(true).build())
                            .supported(true)
                            .xhigh(BetaCapabilitySupport.builder().supported(true).build())
                            .build()
                    )
                    .imageInput(BetaCapabilitySupport.builder().supported(true).build())
                    .pdfInput(BetaCapabilitySupport.builder().supported(true).build())
                    .structuredOutputs(BetaCapabilitySupport.builder().supported(true).build())
                    .thinking(
                        BetaThinkingCapability.builder()
                            .supported(true)
                            .types(
                                BetaThinkingTypes.builder()
                                    .adaptive(
                                        BetaCapabilitySupport.builder().supported(true).build()
                                    )
                                    .enabled(
                                        BetaCapabilitySupport.builder().supported(true).build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
        assertThat(betaModelInfo.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-02-04T00:00:00Z"))
        assertThat(betaModelInfo.displayName()).isEqualTo("Claude Opus 4.6")
        assertThat(betaModelInfo.maxInputTokens()).contains(0L)
        assertThat(betaModelInfo.maxTokens()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaModelInfo =
            BetaModelInfo.builder()
                .id("claude-opus-4-6")
                .capabilities(
                    BetaModelCapabilities.builder()
                        .batch(BetaCapabilitySupport.builder().supported(true).build())
                        .citations(BetaCapabilitySupport.builder().supported(true).build())
                        .codeExecution(BetaCapabilitySupport.builder().supported(true).build())
                        .contextManagement(
                            BetaContextManagementCapability.builder()
                                .clearThinking20251015(
                                    BetaCapabilitySupport.builder().supported(true).build()
                                )
                                .clearToolUses20250919(
                                    BetaCapabilitySupport.builder().supported(true).build()
                                )
                                .compact20260112(
                                    BetaCapabilitySupport.builder().supported(true).build()
                                )
                                .supported(true)
                                .build()
                        )
                        .effort(
                            BetaEffortCapability.builder()
                                .high(BetaCapabilitySupport.builder().supported(true).build())
                                .low(BetaCapabilitySupport.builder().supported(true).build())
                                .max(BetaCapabilitySupport.builder().supported(true).build())
                                .medium(BetaCapabilitySupport.builder().supported(true).build())
                                .supported(true)
                                .xhigh(BetaCapabilitySupport.builder().supported(true).build())
                                .build()
                        )
                        .imageInput(BetaCapabilitySupport.builder().supported(true).build())
                        .pdfInput(BetaCapabilitySupport.builder().supported(true).build())
                        .structuredOutputs(BetaCapabilitySupport.builder().supported(true).build())
                        .thinking(
                            BetaThinkingCapability.builder()
                                .supported(true)
                                .types(
                                    BetaThinkingTypes.builder()
                                        .adaptive(
                                            BetaCapabilitySupport.builder().supported(true).build()
                                        )
                                        .enabled(
                                            BetaCapabilitySupport.builder().supported(true).build()
                                        )
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .createdAt(OffsetDateTime.parse("2026-02-04T00:00:00Z"))
                .displayName("Claude Opus 4.6")
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
