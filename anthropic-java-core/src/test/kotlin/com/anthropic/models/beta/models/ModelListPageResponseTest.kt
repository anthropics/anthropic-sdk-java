// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
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
                        .id("claude-opus-4-6")
                        .capabilities(
                            BetaModelCapabilities.builder()
                                .batch(BetaCapabilitySupport.builder().supported(true).build())
                                .citations(BetaCapabilitySupport.builder().supported(true).build())
                                .codeExecution(
                                    BetaCapabilitySupport.builder().supported(true).build()
                                )
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
                                        .high(
                                            BetaCapabilitySupport.builder().supported(true).build()
                                        )
                                        .low(
                                            BetaCapabilitySupport.builder().supported(true).build()
                                        )
                                        .max(
                                            BetaCapabilitySupport.builder().supported(true).build()
                                        )
                                        .medium(
                                            BetaCapabilitySupport.builder().supported(true).build()
                                        )
                                        .supported(true)
                                        .build()
                                )
                                .imageInput(BetaCapabilitySupport.builder().supported(true).build())
                                .pdfInput(BetaCapabilitySupport.builder().supported(true).build())
                                .structuredOutputs(
                                    BetaCapabilitySupport.builder().supported(true).build()
                                )
                                .thinking(
                                    BetaThinkingCapability.builder()
                                        .supported(true)
                                        .types(
                                            BetaThinkingTypes.builder()
                                                .adaptive(
                                                    BetaCapabilitySupport.builder()
                                                        .supported(true)
                                                        .build()
                                                )
                                                .enabled(
                                                    BetaCapabilitySupport.builder()
                                                        .supported(true)
                                                        .build()
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
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        assertThat(modelListPageResponse.data())
            .containsExactly(
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
                                    .build()
                            )
                            .imageInput(BetaCapabilitySupport.builder().supported(true).build())
                            .pdfInput(BetaCapabilitySupport.builder().supported(true).build())
                            .structuredOutputs(
                                BetaCapabilitySupport.builder().supported(true).build()
                            )
                            .thinking(
                                BetaThinkingCapability.builder()
                                    .supported(true)
                                    .types(
                                        BetaThinkingTypes.builder()
                                            .adaptive(
                                                BetaCapabilitySupport.builder()
                                                    .supported(true)
                                                    .build()
                                            )
                                            .enabled(
                                                BetaCapabilitySupport.builder()
                                                    .supported(true)
                                                    .build()
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
                        .id("claude-opus-4-6")
                        .capabilities(
                            BetaModelCapabilities.builder()
                                .batch(BetaCapabilitySupport.builder().supported(true).build())
                                .citations(BetaCapabilitySupport.builder().supported(true).build())
                                .codeExecution(
                                    BetaCapabilitySupport.builder().supported(true).build()
                                )
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
                                        .high(
                                            BetaCapabilitySupport.builder().supported(true).build()
                                        )
                                        .low(
                                            BetaCapabilitySupport.builder().supported(true).build()
                                        )
                                        .max(
                                            BetaCapabilitySupport.builder().supported(true).build()
                                        )
                                        .medium(
                                            BetaCapabilitySupport.builder().supported(true).build()
                                        )
                                        .supported(true)
                                        .build()
                                )
                                .imageInput(BetaCapabilitySupport.builder().supported(true).build())
                                .pdfInput(BetaCapabilitySupport.builder().supported(true).build())
                                .structuredOutputs(
                                    BetaCapabilitySupport.builder().supported(true).build()
                                )
                                .thinking(
                                    BetaThinkingCapability.builder()
                                        .supported(true)
                                        .types(
                                            BetaThinkingTypes.builder()
                                                .adaptive(
                                                    BetaCapabilitySupport.builder()
                                                        .supported(true)
                                                        .build()
                                                )
                                                .enabled(
                                                    BetaCapabilitySupport.builder()
                                                        .supported(true)
                                                        .build()
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
