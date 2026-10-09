package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTimeoutWorkflowRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsTimeoutWorkflowRunError =
            BetaManagedAgentsTimeoutWorkflowRunError.of("The workflow run reached its time limit.")

        assertThat(betaManagedAgentsTimeoutWorkflowRunError.message())
            .isEqualTo("The workflow run reached its time limit.")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTimeoutWorkflowRunError =
            BetaManagedAgentsTimeoutWorkflowRunError.of("The workflow run reached its time limit.")

        val roundtrippedBetaManagedAgentsTimeoutWorkflowRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTimeoutWorkflowRunError),
                jacksonTypeRef<BetaManagedAgentsTimeoutWorkflowRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTimeoutWorkflowRunError)
            .isEqualTo(betaManagedAgentsTimeoutWorkflowRunError)
    }
}
