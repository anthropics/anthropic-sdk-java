package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsThreadLimitWorkflowRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsThreadLimitWorkflowRunError =
            BetaManagedAgentsThreadLimitWorkflowRunError.of(
                "The workflow run exceeded its limit of threads."
            )

        assertThat(betaManagedAgentsThreadLimitWorkflowRunError.message())
            .isEqualTo("The workflow run exceeded its limit of threads.")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsThreadLimitWorkflowRunError =
            BetaManagedAgentsThreadLimitWorkflowRunError.of(
                "The workflow run exceeded its limit of threads."
            )

        val roundtrippedBetaManagedAgentsThreadLimitWorkflowRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsThreadLimitWorkflowRunError),
                jacksonTypeRef<BetaManagedAgentsThreadLimitWorkflowRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsThreadLimitWorkflowRunError)
            .isEqualTo(betaManagedAgentsThreadLimitWorkflowRunError)
    }
}
