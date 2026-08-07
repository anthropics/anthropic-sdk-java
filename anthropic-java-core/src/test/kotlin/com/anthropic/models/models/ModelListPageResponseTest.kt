// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ModelListPageResponseTest {

    @Test
    fun create() {
        val modelListPageResponse =
            ModelListPageResponse.builder()
                .addData(
                    ModelInfo.builder()
                        .id("claude-opus-4-6")
                        .capabilities(
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
                        )
                        .createdAt(OffsetDateTime.parse("2026-02-04T00:00:00Z"))
                        .displayName("Claude Opus 4.6")
                        .maxInputTokens(0L)
                        .maxTokens(0L)
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        assertThat(modelListPageResponse.data())
            .containsExactly(
                ModelInfo.builder()
                    .id("claude-opus-4-6")
                    .capabilities(
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
                    )
                    .createdAt(OffsetDateTime.parse("2026-02-04T00:00:00Z"))
                    .displayName("Claude Opus 4.6")
                    .maxInputTokens(0L)
                    .maxTokens(0L)
                    .build()
            )
        assertThat(modelListPageResponse.firstId()).contains("first_id")
        assertThat(modelListPageResponse.hasMore()).isEqualTo(true)
        assertThat(modelListPageResponse.lastId()).contains("last_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val modelListPageResponse =
            ModelListPageResponse.builder()
                .addData(
                    ModelInfo.builder()
                        .id("claude-opus-4-6")
                        .capabilities(
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
                        )
                        .createdAt(OffsetDateTime.parse("2026-02-04T00:00:00Z"))
                        .displayName("Claude Opus 4.6")
                        .maxInputTokens(0L)
                        .maxTokens(0L)
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        val roundtrippedModelListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(modelListPageResponse),
                jacksonTypeRef<ModelListPageResponse>(),
            )

        assertThat(roundtrippedModelListPageResponse).isEqualTo(modelListPageResponse)
    }
}
