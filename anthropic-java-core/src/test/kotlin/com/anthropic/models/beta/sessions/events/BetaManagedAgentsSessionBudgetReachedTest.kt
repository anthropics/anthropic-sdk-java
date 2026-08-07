// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionBudgetReachedTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionBudgetReached =
            BetaManagedAgentsSessionBudgetReached.of(
                BetaManagedAgentsSessionBudgetReached.Type.BUDGET_REACHED
            )

        assertThat(betaManagedAgentsSessionBudgetReached.type())
            .isEqualTo(BetaManagedAgentsSessionBudgetReached.Type.BUDGET_REACHED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionBudgetReached =
            BetaManagedAgentsSessionBudgetReached.of(
                BetaManagedAgentsSessionBudgetReached.Type.BUDGET_REACHED
            )

        val roundtrippedBetaManagedAgentsSessionBudgetReached =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionBudgetReached),
                jacksonTypeRef<BetaManagedAgentsSessionBudgetReached>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionBudgetReached)
            .isEqualTo(betaManagedAgentsSessionBudgetReached)
    }
}
