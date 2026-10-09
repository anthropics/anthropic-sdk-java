package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentInlineAgentsDisabledTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentInlineAgentsDisabled =
            BetaManagedAgentsMultiagentInlineAgentsDisabled.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentInlineAgentsDisabled =
            BetaManagedAgentsMultiagentInlineAgentsDisabled.builder().build()

        val roundtrippedBetaManagedAgentsMultiagentInlineAgentsDisabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentInlineAgentsDisabled),
                jacksonTypeRef<BetaManagedAgentsMultiagentInlineAgentsDisabled>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentInlineAgentsDisabled)
            .isEqualTo(betaManagedAgentsMultiagentInlineAgentsDisabled)
    }
}
