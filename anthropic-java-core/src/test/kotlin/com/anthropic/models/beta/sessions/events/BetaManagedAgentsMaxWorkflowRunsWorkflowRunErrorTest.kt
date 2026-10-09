package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMaxWorkflowRunsWorkflowRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMaxWorkflowRunsWorkflowRunError =
            BetaManagedAgentsMaxWorkflowRunsWorkflowRunError.of(
                "The session is at its limit of open workflow runs."
            )

        assertThat(betaManagedAgentsMaxWorkflowRunsWorkflowRunError.message())
            .isEqualTo("The session is at its limit of open workflow runs.")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMaxWorkflowRunsWorkflowRunError =
            BetaManagedAgentsMaxWorkflowRunsWorkflowRunError.of(
                "The session is at its limit of open workflow runs."
            )

        val roundtrippedBetaManagedAgentsMaxWorkflowRunsWorkflowRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMaxWorkflowRunsWorkflowRunError),
                jacksonTypeRef<BetaManagedAgentsMaxWorkflowRunsWorkflowRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMaxWorkflowRunsWorkflowRunError)
            .isEqualTo(betaManagedAgentsMaxWorkflowRunsWorkflowRunError)
    }
}
