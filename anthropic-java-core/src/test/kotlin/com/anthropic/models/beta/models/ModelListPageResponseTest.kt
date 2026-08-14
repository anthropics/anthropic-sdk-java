// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

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
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        assertThat(modelListPageResponse.data())
            .containsExactly(
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
