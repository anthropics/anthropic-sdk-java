package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkflowRunResultErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkflowRunResultError =
            BetaManagedAgentsWorkflowRunResultError.builder()
                .timeoutError("The workflow run reached its time limit.")
                .build()

        assertThat(betaManagedAgentsWorkflowRunResultError.error())
            .isEqualTo(
                BetaManagedAgentsWorkflowRunError.ofTimeout(
                    "The workflow run reached its time limit."
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkflowRunResultError =
            BetaManagedAgentsWorkflowRunResultError.builder()
                .timeoutError("The workflow run reached its time limit.")
                .build()

        val roundtrippedBetaManagedAgentsWorkflowRunResultError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkflowRunResultError),
                jacksonTypeRef<BetaManagedAgentsWorkflowRunResultError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkflowRunResultError)
            .isEqualTo(betaManagedAgentsWorkflowRunResultError)
    }
}
