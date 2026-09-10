package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolEvaluationAlwaysAskTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolEvaluationAlwaysAsk =
            BetaManagedAgentsAgentToolEvaluationAlwaysAsk.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolEvaluationAlwaysAsk =
            BetaManagedAgentsAgentToolEvaluationAlwaysAsk.builder().build()

        val roundtrippedBetaManagedAgentsAgentToolEvaluationAlwaysAsk =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolEvaluationAlwaysAsk),
                jacksonTypeRef<BetaManagedAgentsAgentToolEvaluationAlwaysAsk>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolEvaluationAlwaysAsk)
            .isEqualTo(betaManagedAgentsAgentToolEvaluationAlwaysAsk)
    }
}
