package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsProgramWorkflowRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsProgramWorkflowRunError =
            BetaManagedAgentsProgramWorkflowRunError.of("The workflow run's plan failed.")

        assertThat(betaManagedAgentsProgramWorkflowRunError.message())
            .isEqualTo("The workflow run's plan failed.")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsProgramWorkflowRunError =
            BetaManagedAgentsProgramWorkflowRunError.of("The workflow run's plan failed.")

        val roundtrippedBetaManagedAgentsProgramWorkflowRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsProgramWorkflowRunError),
                jacksonTypeRef<BetaManagedAgentsProgramWorkflowRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsProgramWorkflowRunError)
            .isEqualTo(betaManagedAgentsProgramWorkflowRunError)
    }
}
