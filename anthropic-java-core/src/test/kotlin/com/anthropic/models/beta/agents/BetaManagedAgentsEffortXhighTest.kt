package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEffortXhighTest {

    @Test
    fun create() {
        val betaManagedAgentsEffortXhigh = BetaManagedAgentsEffortXhigh.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEffortXhigh = BetaManagedAgentsEffortXhigh.builder().build()

        val roundtrippedBetaManagedAgentsEffortXhigh =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEffortXhigh),
                jacksonTypeRef<BetaManagedAgentsEffortXhigh>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEffortXhigh).isEqualTo(betaManagedAgentsEffortXhigh)
    }
}
