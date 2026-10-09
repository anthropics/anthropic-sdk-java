package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentInlineAgentsEnabledTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentInlineAgentsEnabled =
            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentInlineAgentsEnabled =
            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()

        val roundtrippedBetaManagedAgentsMultiagentInlineAgentsEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentInlineAgentsEnabled),
                jacksonTypeRef<BetaManagedAgentsMultiagentInlineAgentsEnabled>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentInlineAgentsEnabled)
            .isEqualTo(betaManagedAgentsMultiagentInlineAgentsEnabled)
    }
}
