package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentWorkflowsDisabledParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentWorkflowsDisabledParams =
            BetaManagedAgentsMultiagentWorkflowsDisabledParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentWorkflowsDisabledParams =
            BetaManagedAgentsMultiagentWorkflowsDisabledParams.builder().build()

        val roundtrippedBetaManagedAgentsMultiagentWorkflowsDisabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentWorkflowsDisabledParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentWorkflowsDisabledParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentWorkflowsDisabledParams)
            .isEqualTo(betaManagedAgentsMultiagentWorkflowsDisabledParams)
    }
}
