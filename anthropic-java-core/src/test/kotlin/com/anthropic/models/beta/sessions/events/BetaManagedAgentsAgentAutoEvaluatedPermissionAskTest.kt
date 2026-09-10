package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentAutoEvaluatedPermissionAskTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentAutoEvaluatedPermissionAsk =
            BetaManagedAgentsAgentAutoEvaluatedPermissionAsk.of("reason_code")

        assertThat(betaManagedAgentsAgentAutoEvaluatedPermissionAsk.reasonCode())
            .isEqualTo("reason_code")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentAutoEvaluatedPermissionAsk =
            BetaManagedAgentsAgentAutoEvaluatedPermissionAsk.of("reason_code")

        val roundtrippedBetaManagedAgentsAgentAutoEvaluatedPermissionAsk =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentAutoEvaluatedPermissionAsk),
                jacksonTypeRef<BetaManagedAgentsAgentAutoEvaluatedPermissionAsk>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentAutoEvaluatedPermissionAsk)
            .isEqualTo(betaManagedAgentsAgentAutoEvaluatedPermissionAsk)
    }
}
