package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkflowRunResultCompletedTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkflowRunResultCompleted =
            BetaManagedAgentsWorkflowRunResultCompleted.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunResultCompleted =
            BetaManagedAgentsWorkflowRunResultCompleted.builder().build()

        val roundtrippedBetaManagedAgentsWorkflowRunResultCompleted =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunResultCompleted),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunResultCompleted>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunResultCompleted)
            .isEqualTo(betaManagedAgentsWorkflowRunResultCompleted)
    }
}
