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
                .batch(CapabilitySupport.builder().supported(true).build())
                .citations(CapabilitySupport.builder().supported(true).build())
                .codeExecution(CapabilitySupport.builder().supported(true).build())
                .contextManagement(
                    ContextManagementCapability.builder()
                        .clearThinking20251015(CapabilitySupport.builder().supported(true).build())
                        .clearToolUses20250919(CapabilitySupport.builder().supported(true).build())
                        .compact20260112(CapabilitySupport.builder().supported(true).build())
                        .supported(true)
                        .build()
                )
                .effort(
                    EffortCapability.builder()
                        .high(CapabilitySupport.builder().supported(true).build())
                        .low(CapabilitySupport.builder().supported(true).build())
                        .max(CapabilitySupport.builder().supported(true).build())
                        .medium(CapabilitySupport.builder().supported(true).build())
                        .supported(true)
                        .xhigh(CapabilitySupport.builder().supported(true).build())
                        .build()
                )
                .imageInput(CapabilitySupport.builder().supported(true).build())
                .pdfInput(CapabilitySupport.builder().supported(true).build())
                .structuredOutputs(CapabilitySupport.builder().supported(true).build())
                .thinking(
                    ThinkingCapability.builder()
                        .supported(true)
                        .types(
                            ThinkingTypes.builder()
                                .adaptive(CapabilitySupport.builder().supported(true).build())
                                .enabled(CapabilitySupport.builder().supported(true).build())
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(modelCapabilities.batch())
            .isEqualTo(CapabilitySupport.builder().supported(true).build())
        assertThat(modelCapabilities.citations())
            .isEqualTo(CapabilitySupport.builder().supported(true).build())
        assertThat(modelCapabilities.codeExecution())
            .isEqualTo(CapabilitySupport.builder().supported(true).build())
        assertThat(modelCapabilities.contextManagement())
            .isEqualTo(
                ContextManagementCapability.builder()
                    .clearThinking20251015(CapabilitySupport.builder().supported(true).build())
                    .clearToolUses20250919(CapabilitySupport.builder().supported(true).build())
                    .compact20260112(CapabilitySupport.builder().supported(true).build())
                    .supported(true)
                    .build()
            )
        assertThat(modelCapabilities.effort())
            .isEqualTo(
                EffortCapability.builder()
                    .high(CapabilitySupport.builder().supported(true).build())
                    .low(CapabilitySupport.builder().supported(true).build())
                    .max(CapabilitySupport.builder().supported(true).build())
                    .medium(CapabilitySupport.builder().supported(true).build())
                    .supported(true)
                    .xhigh(CapabilitySupport.builder().supported(true).build())
                    .build()
            )
        assertThat(modelCapabilities.imageInput())
            .isEqualTo(CapabilitySupport.builder().supported(true).build())
        assertThat(modelCapabilities.pdfInput())
            .isEqualTo(CapabilitySupport.builder().supported(true).build())
        assertThat(modelCapabilities.structuredOutputs())
            .isEqualTo(CapabilitySupport.builder().supported(true).build())
        assertThat(modelCapabilities.thinking())
            .isEqualTo(
                ThinkingCapability.builder()
                    .supported(true)
                    .types(
                        ThinkingTypes.builder()
                            .adaptive(CapabilitySupport.builder().supported(true).build())
                            .enabled(CapabilitySupport.builder().supported(true).build())
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
                .batch(CapabilitySupport.builder().supported(true).build())
                .citations(CapabilitySupport.builder().supported(true).build())
                .codeExecution(CapabilitySupport.builder().supported(true).build())
                .contextManagement(
                    ContextManagementCapability.builder()
                        .clearThinking20251015(CapabilitySupport.builder().supported(true).build())
                        .clearToolUses20250919(CapabilitySupport.builder().supported(true).build())
                        .compact20260112(CapabilitySupport.builder().supported(true).build())
                        .supported(true)
                        .build()
                )
                .effort(
                    EffortCapability.builder()
                        .high(CapabilitySupport.builder().supported(true).build())
                        .low(CapabilitySupport.builder().supported(true).build())
                        .max(CapabilitySupport.builder().supported(true).build())
                        .medium(CapabilitySupport.builder().supported(true).build())
                        .supported(true)
                        .xhigh(CapabilitySupport.builder().supported(true).build())
                        .build()
                )
                .imageInput(CapabilitySupport.builder().supported(true).build())
                .pdfInput(CapabilitySupport.builder().supported(true).build())
                .structuredOutputs(CapabilitySupport.builder().supported(true).build())
                .thinking(
                    ThinkingCapability.builder()
                        .supported(true)
                        .types(
                            ThinkingTypes.builder()
                                .adaptive(CapabilitySupport.builder().supported(true).build())
                                .enabled(CapabilitySupport.builder().supported(true).build())
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
