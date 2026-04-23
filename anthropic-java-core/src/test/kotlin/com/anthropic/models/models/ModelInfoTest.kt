// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ModelInfoTest {

    @Test
    fun create() {
        val modelInfo =
            ModelInfo.builder()
                .id("claude-opus-4-6")
                .capabilities(
                    ModelCapabilities.builder()
                        .batch(CapabilitySupport.builder().supported(true).build())
                        .citations(CapabilitySupport.builder().supported(true).build())
                        .codeExecution(CapabilitySupport.builder().supported(true).build())
                        .contextManagement(
                            ContextManagementCapability.builder()
                                .clearThinking20251015(
                                    CapabilitySupport.builder().supported(true).build()
                                )
                                .clearToolUses20250919(
                                    CapabilitySupport.builder().supported(true).build()
                                )
                                .compact20260112(
                                    CapabilitySupport.builder().supported(true).build()
                                )
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
                                        .adaptive(
                                            CapabilitySupport.builder().supported(true).build()
                                        )
                                        .enabled(
                                            CapabilitySupport.builder().supported(true).build()
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

        assertThat(modelInfo.id()).isEqualTo("claude-opus-4-6")
        assertThat(modelInfo.capabilities())
            .contains(
                ModelCapabilities.builder()
                    .batch(CapabilitySupport.builder().supported(true).build())
                    .citations(CapabilitySupport.builder().supported(true).build())
                    .codeExecution(CapabilitySupport.builder().supported(true).build())
                    .contextManagement(
                        ContextManagementCapability.builder()
                            .clearThinking20251015(
                                CapabilitySupport.builder().supported(true).build()
                            )
                            .clearToolUses20250919(
                                CapabilitySupport.builder().supported(true).build()
                            )
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
            )
        assertThat(modelInfo.createdAt()).isEqualTo(OffsetDateTime.parse("2026-02-04T00:00:00Z"))
        assertThat(modelInfo.displayName()).isEqualTo("Claude Opus 4.6")
        assertThat(modelInfo.maxInputTokens()).contains(0L)
        assertThat(modelInfo.maxTokens()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val modelInfo =
            ModelInfo.builder()
                .id("claude-opus-4-6")
                .capabilities(
                    ModelCapabilities.builder()
                        .batch(CapabilitySupport.builder().supported(true).build())
                        .citations(CapabilitySupport.builder().supported(true).build())
                        .codeExecution(CapabilitySupport.builder().supported(true).build())
                        .contextManagement(
                            ContextManagementCapability.builder()
                                .clearThinking20251015(
                                    CapabilitySupport.builder().supported(true).build()
                                )
                                .clearToolUses20250919(
                                    CapabilitySupport.builder().supported(true).build()
                                )
                                .compact20260112(
                                    CapabilitySupport.builder().supported(true).build()
                                )
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
                                        .adaptive(
                                            CapabilitySupport.builder().supported(true).build()
                                        )
                                        .enabled(
                                            CapabilitySupport.builder().supported(true).build()
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

        val roundtrippedModelInfo =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(modelInfo),
                jacksonTypeRef<ModelInfo>(),
            )

        assertThat(roundtrippedModelInfo).isEqualTo(modelInfo)
    }
}
