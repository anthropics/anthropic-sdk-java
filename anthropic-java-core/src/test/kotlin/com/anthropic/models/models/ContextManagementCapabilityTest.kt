// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContextManagementCapabilityTest {

    @Test
    fun create() {
        val contextManagementCapability =
            ContextManagementCapability.builder()
                .clearThinking20251015(CapabilitySupport.of(true))
                .clearToolUses20250919(CapabilitySupport.of(true))
                .compact20260112(CapabilitySupport.of(true))
                .supported(true)
                .build()

        assertThat(contextManagementCapability.clearThinking20251015())
            .contains(CapabilitySupport.of(true))
        assertThat(contextManagementCapability.clearToolUses20250919())
            .contains(CapabilitySupport.of(true))
        assertThat(contextManagementCapability.compact20260112())
            .contains(CapabilitySupport.of(true))
        assertThat(contextManagementCapability.supported()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val contextManagementCapability =
            ContextManagementCapability.builder()
                .clearThinking20251015(CapabilitySupport.of(true))
                .clearToolUses20250919(CapabilitySupport.of(true))
                .compact20260112(CapabilitySupport.of(true))
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
