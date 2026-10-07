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
                .id("claude-opus-5")
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
                        .serverTools(
                            ServerToolsCapability.builder()
                                .codeExecution(CapabilitySupport.of(true))
                                .supported(true)
                                .webSearch(CapabilitySupport.of(true))
                                .build()
                        )
                        .structuredOutputs(CapabilitySupport.of(true))
                        .thinking(
                            ThinkingCapability.builder()
                                .supported(true)
                                .types(
                                    ThinkingTypes.builder()
                                        .adaptive(CapabilitySupport.of(true))
                                        .disabled(CapabilitySupport.of(true))
                                        .enabled(CapabilitySupport.of(true))
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .createdAt(OffsetDateTime.parse("2026-07-24T00:00:00Z"))
                .deprecatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .displayName("Claude Opus 5")
                .lifecycle(ModelInfo.Lifecycle.ACTIVE)
                .line(ModelLine.HAIKU)
                .maxInputTokens(0L)
                .maxTokens(0L)
                .retiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(modelInfo.id()).isEqualTo("claude-opus-5")
        assertThat(modelInfo.capabilities())
            .contains(
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
                    .serverTools(
                        ServerToolsCapability.builder()
                            .codeExecution(CapabilitySupport.of(true))
                            .supported(true)
                            .webSearch(CapabilitySupport.of(true))
                            .build()
                    )
                    .structuredOutputs(CapabilitySupport.of(true))
                    .thinking(
                        ThinkingCapability.builder()
                            .supported(true)
                            .types(
                                ThinkingTypes.builder()
                                    .adaptive(CapabilitySupport.of(true))
                                    .disabled(CapabilitySupport.of(true))
                                    .enabled(CapabilitySupport.of(true))
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
        assertThat(modelInfo.createdAt()).isEqualTo(OffsetDateTime.parse("2026-07-24T00:00:00Z"))
        assertThat(modelInfo.deprecatedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(modelInfo.displayName()).isEqualTo("Claude Opus 5")
        assertThat(modelInfo.lifecycle()).isEqualTo(ModelInfo.Lifecycle.ACTIVE)
        assertThat(modelInfo.line()).contains(ModelLine.HAIKU)
        assertThat(modelInfo.maxInputTokens()).contains(0L)
        assertThat(modelInfo.maxTokens()).contains(0L)
        assertThat(modelInfo.retiresAt()).contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val modelInfo =
            ModelInfo.builder()
                .id("claude-opus-5")
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
                        .serverTools(
                            ServerToolsCapability.builder()
                                .codeExecution(CapabilitySupport.of(true))
                                .supported(true)
                                .webSearch(CapabilitySupport.of(true))
                                .build()
                        )
                        .structuredOutputs(CapabilitySupport.of(true))
                        .thinking(
                            ThinkingCapability.builder()
                                .supported(true)
                                .types(
                                    ThinkingTypes.builder()
                                        .adaptive(CapabilitySupport.of(true))
                                        .disabled(CapabilitySupport.of(true))
                                        .enabled(CapabilitySupport.of(true))
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .createdAt(OffsetDateTime.parse("2026-07-24T00:00:00Z"))
                .deprecatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .displayName("Claude Opus 5")
                .lifecycle(ModelInfo.Lifecycle.ACTIVE)
                .line(ModelLine.HAIKU)
                .maxInputTokens(0L)
                .maxTokens(0L)
                .retiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedModelInfo =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(modelInfo),
                jacksonTypeRef<ModelInfo>(),
            )

        assertThat(roundtrippedModelInfo).isEqualTo(modelInfo)
    }
}
