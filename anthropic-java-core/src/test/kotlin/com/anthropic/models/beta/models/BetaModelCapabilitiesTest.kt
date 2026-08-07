// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaModelCapabilitiesTest {

    @Test
    fun create() {
        val betaModelCapabilities =
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

        assertThat(betaModelCapabilities.batch()).isEqualTo(BetaCapabilitySupport.of(true))
        assertThat(betaModelCapabilities.citations()).isEqualTo(BetaCapabilitySupport.of(true))
        assertThat(betaModelCapabilities.codeExecution()).isEqualTo(BetaCapabilitySupport.of(true))
        assertThat(betaModelCapabilities.contextManagement())
            .isEqualTo(
                BetaContextManagementCapability.builder()
                    .clearThinking20251015(BetaCapabilitySupport.of(true))
                    .clearToolUses20250919(BetaCapabilitySupport.of(true))
                    .compact20260112(BetaCapabilitySupport.of(true))
                    .supported(true)
                    .build()
            )
        assertThat(betaModelCapabilities.effort())
            .isEqualTo(
                BetaEffortCapability.builder()
                    .high(BetaCapabilitySupport.of(true))
                    .low(BetaCapabilitySupport.of(true))
                    .max(BetaCapabilitySupport.of(true))
                    .medium(BetaCapabilitySupport.of(true))
                    .supported(true)
                    .xhigh(BetaCapabilitySupport.of(true))
                    .build()
            )
        assertThat(betaModelCapabilities.imageInput()).isEqualTo(BetaCapabilitySupport.of(true))
        assertThat(betaModelCapabilities.pdfInput()).isEqualTo(BetaCapabilitySupport.of(true))
        assertThat(betaModelCapabilities.structuredOutputs())
            .isEqualTo(BetaCapabilitySupport.of(true))
        assertThat(betaModelCapabilities.thinking())
            .isEqualTo(
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
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaModelCapabilities =
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

        val roundtrippedBetaModelCapabilities =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaModelCapabilities),
                jacksonTypeRef<BetaModelCapabilities>(),
            )

        assertThat(roundtrippedBetaModelCapabilities).isEqualTo(betaModelCapabilities)
    }
}
