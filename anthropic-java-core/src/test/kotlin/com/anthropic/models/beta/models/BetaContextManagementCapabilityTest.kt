// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContextManagementCapabilityTest {

    @Test
    fun create() {
        val betaContextManagementCapability =
            BetaContextManagementCapability.builder()
                .clearThinking20251015(BetaCapabilitySupport.of(true))
                .clearToolUses20250919(BetaCapabilitySupport.of(true))
                .compact20260112(BetaCapabilitySupport.of(true))
                .supported(true)
                .build()

        assertThat(betaContextManagementCapability.clearThinking20251015())
            .contains(BetaCapabilitySupport.of(true))
        assertThat(betaContextManagementCapability.clearToolUses20250919())
            .contains(BetaCapabilitySupport.of(true))
        assertThat(betaContextManagementCapability.compact20260112())
            .contains(BetaCapabilitySupport.of(true))
        assertThat(betaContextManagementCapability.supported()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaContextManagementCapability =
            BetaContextManagementCapability.builder()
                .clearThinking20251015(BetaCapabilitySupport.of(true))
                .clearToolUses20250919(BetaCapabilitySupport.of(true))
                .compact20260112(BetaCapabilitySupport.of(true))
                .supported(true)
                .build()

        val roundtrippedBetaContextManagementCapability =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContextManagementCapability),
                jacksonTypeRef<BetaContextManagementCapability>(),
            )

        assertThat(roundtrippedBetaContextManagementCapability)
            .isEqualTo(betaContextManagementCapability)
    }
}
