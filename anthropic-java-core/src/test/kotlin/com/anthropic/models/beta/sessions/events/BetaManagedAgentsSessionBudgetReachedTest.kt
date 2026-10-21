package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionBudgetReachedTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionBudgetReached =
            BetaManagedAgentsSessionBudgetReached.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionBudgetReached =
            BetaManagedAgentsSessionBudgetReached.builder().build()

        val roundtrippedBetaManagedAgentsSessionBudgetReached =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionBudgetReached),
                jacksonTypeRef<BetaManagedAgentsSessionBudgetReached>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionBudgetReached)
            .isEqualTo(betaManagedAgentsSessionBudgetReached)
    }
}
