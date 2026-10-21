package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEffortMaxTest {

    @Test
    fun create() {
        val betaManagedAgentsEffortMax = BetaManagedAgentsEffortMax.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEffortMax = BetaManagedAgentsEffortMax.builder().build()

        val roundtrippedBetaManagedAgentsEffortMax =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEffortMax),
                jacksonTypeRef<BetaManagedAgentsEffortMax>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEffortMax).isEqualTo(betaManagedAgentsEffortMax)
    }
}
