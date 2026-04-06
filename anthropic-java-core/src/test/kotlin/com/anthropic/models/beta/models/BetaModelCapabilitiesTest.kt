// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaModelCapabilitiesTest {

    @Test
    fun create() {
        val betaModelCapabilities =
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
                        .compact20260112(BetaCapabilitySupport.builder().supported(true).build())
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
                                .adaptive(BetaCapabilitySupport.builder().supported(true).build())
                                .enabled(BetaCapabilitySupport.builder().supported(true).build())
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(betaModelCapabilities.batch())
            .isEqualTo(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaModelCapabilities.citations())
            .isEqualTo(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaModelCapabilities.codeExecution())
            .isEqualTo(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaModelCapabilities.contextManagement())
            .isEqualTo(
                BetaContextManagementCapability.builder()
                    .clearThinking20251015(BetaCapabilitySupport.builder().supported(true).build())
                    .clearToolUses20250919(BetaCapabilitySupport.builder().supported(true).build())
                    .compact20260112(BetaCapabilitySupport.builder().supported(true).build())
                    .supported(true)
                    .build()
            )
        assertThat(betaModelCapabilities.effort())
            .isEqualTo(
                BetaEffortCapability.builder()
                    .high(BetaCapabilitySupport.builder().supported(true).build())
                    .low(BetaCapabilitySupport.builder().supported(true).build())
                    .max(BetaCapabilitySupport.builder().supported(true).build())
                    .medium(BetaCapabilitySupport.builder().supported(true).build())
                    .supported(true)
                    .build()
            )
        assertThat(betaModelCapabilities.imageInput())
            .isEqualTo(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaModelCapabilities.pdfInput())
            .isEqualTo(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaModelCapabilities.structuredOutputs())
            .isEqualTo(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaModelCapabilities.thinking())
            .isEqualTo(
                BetaThinkingCapability.builder()
                    .supported(true)
                    .types(
                        BetaThinkingTypes.builder()
                            .adaptive(BetaCapabilitySupport.builder().supported(true).build())
                            .enabled(BetaCapabilitySupport.builder().supported(true).build())
                            .build()
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaModelCapabilities =
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
                        .compact20260112(BetaCapabilitySupport.builder().supported(true).build())
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
                                .adaptive(BetaCapabilitySupport.builder().supported(true).build())
                                .enabled(BetaCapabilitySupport.builder().supported(true).build())
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaModelCapabilities =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaModelCapabilities),
                jacksonTypeRef<BetaModelCapabilities>(),
            )

        assertThat(roundtrippedBetaModelCapabilities).isEqualTo(betaModelCapabilities)
    }
}
