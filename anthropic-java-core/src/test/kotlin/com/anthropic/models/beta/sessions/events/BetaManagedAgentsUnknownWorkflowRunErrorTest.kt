package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUnknownWorkflowRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsUnknownWorkflowRunError =
            BetaManagedAgentsUnknownWorkflowRunError.of("The workflow run failed.")

        assertThat(betaManagedAgentsUnknownWorkflowRunError.message())
            .isEqualTo("The workflow run failed.")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUnknownWorkflowRunError =
            BetaManagedAgentsUnknownWorkflowRunError.of("The workflow run failed.")

        val roundtrippedBetaManagedAgentsUnknownWorkflowRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUnknownWorkflowRunError),
                jacksonTypeRef<BetaManagedAgentsUnknownWorkflowRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUnknownWorkflowRunError)
            .isEqualTo(betaManagedAgentsUnknownWorkflowRunError)
    }
}
