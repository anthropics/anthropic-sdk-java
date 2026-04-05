// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import kotlinx.kmp.util.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContextManagementCapabilityTest {

    @Test
    fun create() {
        val betaContextManagementCapability =
            BetaContextManagementCapability.builder()
                .clearThinking20251015(BetaCapabilitySupport.builder().supported(true).build())
                .clearToolUses20250919(BetaCapabilitySupport.builder().supported(true).build())
                .compact20260112(BetaCapabilitySupport.builder().supported(true).build())
                .supported(true)
                .build()

        assertThat(betaContextManagementCapability.clearThinking20251015())
            .contains(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaContextManagementCapability.clearToolUses20250919())
            .contains(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaContextManagementCapability.compact20260112())
            .contains(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaContextManagementCapability.supported()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaContextManagementCapability =
            BetaContextManagementCapability.builder()
                .clearThinking20251015(BetaCapabilitySupport.builder().supported(true).build())
                .clearToolUses20250919(BetaCapabilitySupport.builder().supported(true).build())
                .compact20260112(BetaCapabilitySupport.builder().supported(true).build())
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
