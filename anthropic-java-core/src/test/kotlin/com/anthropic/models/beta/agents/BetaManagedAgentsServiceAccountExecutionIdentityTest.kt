package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsServiceAccountExecutionIdentityTest {

    @Test
    fun create() {
        val betaManagedAgentsServiceAccountExecutionIdentity =
            BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsServiceAccountExecutionIdentity =
            BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()

        val roundtrippedBetaManagedAgentsServiceAccountExecutionIdentity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsServiceAccountExecutionIdentity),
                jacksonTypeRef<BetaManagedAgentsServiceAccountExecutionIdentity>(),
            )

        assertThat(roundtrippedBetaManagedAgentsServiceAccountExecutionIdentity)
            .isEqualTo(betaManagedAgentsServiceAccountExecutionIdentity)
    }
}
