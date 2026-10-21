package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpEgressBlockedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpEgressBlockedRunError =
            BetaManagedAgentsMcpEgressBlockedRunError.of("message")

        assertThat(betaManagedAgentsMcpEgressBlockedRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpEgressBlockedRunError =
            BetaManagedAgentsMcpEgressBlockedRunError.of("message")

        val roundtrippedBetaManagedAgentsMcpEgressBlockedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpEgressBlockedRunError),
                jacksonTypeRef<BetaManagedAgentsMcpEgressBlockedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpEgressBlockedRunError)
            .isEqualTo(betaManagedAgentsMcpEgressBlockedRunError)
    }
}
