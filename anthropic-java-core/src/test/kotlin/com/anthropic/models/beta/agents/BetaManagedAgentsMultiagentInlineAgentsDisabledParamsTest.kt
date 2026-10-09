package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentInlineAgentsDisabledParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentInlineAgentsDisabledParams =
            BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentInlineAgentsDisabledParams =
            BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()

        val roundtrippedBetaManagedAgentsMultiagentInlineAgentsDisabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsMultiagentInlineAgentsDisabledParams
                ),
                jacksonTypeRef<BetaManagedAgentsMultiagentInlineAgentsDisabledParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentInlineAgentsDisabledParams)
            .isEqualTo(betaManagedAgentsMultiagentInlineAgentsDisabledParams)
    }
}
