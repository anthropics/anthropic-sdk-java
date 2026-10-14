package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsServiceAccountExecutionIdentityParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsServiceAccountExecutionIdentityParams =
            BetaManagedAgentsServiceAccountExecutionIdentityParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsServiceAccountExecutionIdentityParams =
            BetaManagedAgentsServiceAccountExecutionIdentityParams.builder().build()

        val roundtrippedBetaManagedAgentsServiceAccountExecutionIdentityParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsServiceAccountExecutionIdentityParams
                ),
                jacksonTypeRef<BetaManagedAgentsServiceAccountExecutionIdentityParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsServiceAccountExecutionIdentityParams)
            .isEqualTo(betaManagedAgentsServiceAccountExecutionIdentityParams)
    }
}
