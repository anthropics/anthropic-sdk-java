// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCacheCreationUsageTest {

    @Test
    fun create() {
        val betaManagedAgentsCacheCreationUsage =
            BetaManagedAgentsCacheCreationUsage.builder()
                .ephemeral1hInputTokens(0)
                .ephemeral5mInputTokens(0)
                .build()

        assertThat(betaManagedAgentsCacheCreationUsage.ephemeral1hInputTokens()).contains(0)
        assertThat(betaManagedAgentsCacheCreationUsage.ephemeral5mInputTokens()).contains(0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCacheCreationUsage =
            BetaManagedAgentsCacheCreationUsage.builder()
                .ephemeral1hInputTokens(0)
                .ephemeral5mInputTokens(0)
                .build()

        val roundtrippedBetaManagedAgentsCacheCreationUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCacheCreationUsage),
                jacksonTypeRef<BetaManagedAgentsCacheCreationUsage>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCacheCreationUsage)
            .isEqualTo(betaManagedAgentsCacheCreationUsage)
    }
}
