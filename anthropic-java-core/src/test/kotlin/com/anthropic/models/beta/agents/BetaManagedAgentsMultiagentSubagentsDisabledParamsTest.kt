package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentSubagentsDisabledParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentSubagentsDisabledParams =
            BetaManagedAgentsMultiagentSubagentsDisabledParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentSubagentsDisabledParams =
            BetaManagedAgentsMultiagentSubagentsDisabledParams.builder().build()

        val roundtrippedBetaManagedAgentsMultiagentSubagentsDisabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentSubagentsDisabledParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentSubagentsDisabledParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentSubagentsDisabledParams)
            .isEqualTo(betaManagedAgentsMultiagentSubagentsDisabledParams)
    }
}
