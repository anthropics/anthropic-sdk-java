package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEffortMediumTest {

    @Test
    fun create() {
        val betaManagedAgentsEffortMedium = BetaManagedAgentsEffortMedium.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEffortMedium = BetaManagedAgentsEffortMedium.builder().build()

        val roundtrippedBetaManagedAgentsEffortMedium =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEffortMedium),
                jacksonTypeRef<BetaManagedAgentsEffortMedium>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEffortMedium)
            .isEqualTo(betaManagedAgentsEffortMedium)
    }
}
