package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentAdvisorDisabledParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentAdvisorDisabledParams =
            BetaManagedAgentsMultiagentAdvisorDisabledParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentAdvisorDisabledParams =
            BetaManagedAgentsMultiagentAdvisorDisabledParams.builder().build()

        val roundtrippedBetaManagedAgentsMultiagentAdvisorDisabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentAdvisorDisabledParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentAdvisorDisabledParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentAdvisorDisabledParams)
            .isEqualTo(betaManagedAgentsMultiagentAdvisorDisabledParams)
    }
}
