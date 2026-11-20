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
                .ephemeral1hInputTokens(0L)
                .ephemeral5mInputTokens(0L)
                .build()

        assertThat(betaManagedAgentsCacheCreationUsage.ephemeral1hInputTokens()).contains(0L)
        assertThat(betaManagedAgentsCacheCreationUsage.ephemeral5mInputTokens()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCacheCreationUsage =
            BetaManagedAgentsCacheCreationUsage.builder()
                .ephemeral1hInputTokens(0L)
                .ephemeral5mInputTokens(0L)
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
