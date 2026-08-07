// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ModelCapabilitiesTest {

    @Test
    fun create() {
        val modelCapabilities =
            ModelCapabilities.builder()
                .batch(CapabilitySupport.of(true))
                .citations(CapabilitySupport.of(true))
                .codeExecution(CapabilitySupport.of(true))
                .contextManagement(
                    ContextManagementCapability.builder()
                        .clearThinking20251015(CapabilitySupport.of(true))
                        .clearToolUses20250919(CapabilitySupport.of(true))
                        .compact20260112(CapabilitySupport.of(true))
                        .supported(true)
                        .build()
                )
                .effort(
                    EffortCapability.builder()
                        .high(CapabilitySupport.of(true))
                        .low(CapabilitySupport.of(true))
                        .max(CapabilitySupport.of(true))
                        .medium(CapabilitySupport.of(true))
                        .supported(true)
                        .xhigh(CapabilitySupport.of(true))
                        .build()
                )
                .imageInput(CapabilitySupport.of(true))
                .pdfInput(CapabilitySupport.of(true))
                .structuredOutputs(CapabilitySupport.of(true))
                .thinking(
                    ThinkingCapability.builder()
                        .supported(true)
                        .types(
                            ThinkingTypes.builder()
                                .adaptive(CapabilitySupport.of(true))
                                .enabled(CapabilitySupport.of(true))
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(modelCapabilities.batch()).isEqualTo(CapabilitySupport.of(true))
        assertThat(modelCapabilities.citations()).isEqualTo(CapabilitySupport.of(true))
        assertThat(modelCapabilities.codeExecution()).isEqualTo(CapabilitySupport.of(true))
        assertThat(modelCapabilities.contextManagement())
            .isEqualTo(
                ContextManagementCapability.builder()
                    .clearThinking20251015(CapabilitySupport.of(true))
                    .clearToolUses20250919(CapabilitySupport.of(true))
                    .compact20260112(CapabilitySupport.of(true))
                    .supported(true)
                    .build()
            )
        assertThat(modelCapabilities.effort())
            .isEqualTo(
                EffortCapability.builder()
                    .high(CapabilitySupport.of(true))
                    .low(CapabilitySupport.of(true))
                    .max(CapabilitySupport.of(true))
                    .medium(CapabilitySupport.of(true))
                    .supported(true)
                    .xhigh(CapabilitySupport.of(true))
                    .build()
            )
        assertThat(modelCapabilities.imageInput()).isEqualTo(CapabilitySupport.of(true))
        assertThat(modelCapabilities.pdfInput()).isEqualTo(CapabilitySupport.of(true))
        assertThat(modelCapabilities.structuredOutputs()).isEqualTo(CapabilitySupport.of(true))
        assertThat(modelCapabilities.thinking())
            .isEqualTo(
                ThinkingCapability.builder()
                    .supported(true)
                    .types(
                        ThinkingTypes.builder()
                            .adaptive(CapabilitySupport.of(true))
                            .enabled(CapabilitySupport.of(true))
                            .build()
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val modelCapabilities =
            ModelCapabilities.builder()
                .batch(CapabilitySupport.of(true))
                .citations(CapabilitySupport.of(true))
                .codeExecution(CapabilitySupport.of(true))
                .contextManagement(
                    ContextManagementCapability.builder()
                        .clearThinking20251015(CapabilitySupport.of(true))
                        .clearToolUses20250919(CapabilitySupport.of(true))
                        .compact20260112(CapabilitySupport.of(true))
                        .supported(true)
                        .build()
                )
                .effort(
                    EffortCapability.builder()
                        .high(CapabilitySupport.of(true))
                        .low(CapabilitySupport.of(true))
                        .max(CapabilitySupport.of(true))
                        .medium(CapabilitySupport.of(true))
                        .supported(true)
                        .xhigh(CapabilitySupport.of(true))
                        .build()
                )
                .imageInput(CapabilitySupport.of(true))
                .pdfInput(CapabilitySupport.of(true))
                .structuredOutputs(CapabilitySupport.of(true))
                .thinking(
                    ThinkingCapability.builder()
                        .supported(true)
                        .types(
                            ThinkingTypes.builder()
                                .adaptive(CapabilitySupport.of(true))
                                .enabled(CapabilitySupport.of(true))
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedModelCapabilities =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(modelCapabilities),
                jacksonTypeRef<ModelCapabilities>(),
            )

        assertThat(roundtrippedModelCapabilities).isEqualTo(modelCapabilities)
    }
}
