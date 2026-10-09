package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkflowRunResultStoppedTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkflowRunResultStopped =
            BetaManagedAgentsWorkflowRunResultStopped.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunResultStopped =
            BetaManagedAgentsWorkflowRunResultStopped.builder().build()

        val roundtrippedBetaManagedAgentsWorkflowRunResultStopped =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunResultStopped),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunResultStopped>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunResultStopped)
            .isEqualTo(betaManagedAgentsWorkflowRunResultStopped)
    }
}
