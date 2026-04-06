// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import kotlinx.kmp.util.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContextManagementCapabilityTest {

    @Test
    fun create() {
        val contextManagementCapability =
            ContextManagementCapability.builder()
                .clearThinking20251015(CapabilitySupport.builder().supported(true).build())
                .clearToolUses20250919(CapabilitySupport.builder().supported(true).build())
                .compact20260112(CapabilitySupport.builder().supported(true).build())
                .supported(true)
                .build()

        assertThat(contextManagementCapability.clearThinking20251015())
            .contains(CapabilitySupport.builder().supported(true).build())
        assertThat(contextManagementCapability.clearToolUses20250919())
            .contains(CapabilitySupport.builder().supported(true).build())
        assertThat(contextManagementCapability.compact20260112())
            .contains(CapabilitySupport.builder().supported(true).build())
        assertThat(contextManagementCapability.supported()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val contextManagementCapability =
            ContextManagementCapability.builder()
                .clearThinking20251015(CapabilitySupport.builder().supported(true).build())
                .clearToolUses20250919(CapabilitySupport.builder().supported(true).build())
                .compact20260112(CapabilitySupport.builder().supported(true).build())
                .supported(true)
                .build()

        val roundtrippedContextManagementCapability =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contextManagementCapability),
                jacksonTypeRef<ContextManagementCapability>(),
            )

        assertThat(roundtrippedContextManagementCapability).isEqualTo(contextManagementCapability)
    }
}
