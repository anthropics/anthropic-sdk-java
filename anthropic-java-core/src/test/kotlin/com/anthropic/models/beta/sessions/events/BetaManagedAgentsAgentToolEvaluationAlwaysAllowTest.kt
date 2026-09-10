package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolEvaluationAlwaysAllowTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolEvaluationAlwaysAllow =
            BetaManagedAgentsAgentToolEvaluationAlwaysAllow.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolEvaluationAlwaysAllow =
            BetaManagedAgentsAgentToolEvaluationAlwaysAllow.builder().build()

        val roundtrippedBetaManagedAgentsAgentToolEvaluationAlwaysAllow =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolEvaluationAlwaysAllow),
                jacksonTypeRef<BetaManagedAgentsAgentToolEvaluationAlwaysAllow>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolEvaluationAlwaysAllow)
            .isEqualTo(betaManagedAgentsAgentToolEvaluationAlwaysAllow)
    }
}
