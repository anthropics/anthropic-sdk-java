package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentSelfParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentSelfParams =
            BetaManagedAgentsMultiagentSelfParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentSelfParams =
            BetaManagedAgentsMultiagentSelfParams.builder().build()

        val roundtrippedBetaManagedAgentsMultiagentSelfParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentSelfParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentSelfParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentSelfParams)
            .isEqualTo(betaManagedAgentsMultiagentSelfParams)
    }
}
