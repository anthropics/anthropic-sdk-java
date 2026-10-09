package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentInlineAgentsEnabledParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentInlineAgentsEnabledParams =
            BetaManagedAgentsMultiagentInlineAgentsEnabledParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentInlineAgentsEnabledParams =
            BetaManagedAgentsMultiagentInlineAgentsEnabledParams.builder().build()

        val roundtrippedBetaManagedAgentsMultiagentInlineAgentsEnabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentInlineAgentsEnabledParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentInlineAgentsEnabledParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentInlineAgentsEnabledParams)
            .isEqualTo(betaManagedAgentsMultiagentInlineAgentsEnabledParams)
    }
}
