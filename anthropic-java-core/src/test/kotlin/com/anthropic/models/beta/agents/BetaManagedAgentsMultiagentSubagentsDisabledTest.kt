package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentSubagentsDisabledTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentSubagentsDisabled =
            BetaManagedAgentsMultiagentSubagentsDisabled.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentSubagentsDisabled =
            BetaManagedAgentsMultiagentSubagentsDisabled.builder().build()

        val roundtrippedBetaManagedAgentsMultiagentSubagentsDisabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentSubagentsDisabled),
                jacksonTypeRef<BetaManagedAgentsMultiagentSubagentsDisabled>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentSubagentsDisabled)
            .isEqualTo(betaManagedAgentsMultiagentSubagentsDisabled)
    }
}
