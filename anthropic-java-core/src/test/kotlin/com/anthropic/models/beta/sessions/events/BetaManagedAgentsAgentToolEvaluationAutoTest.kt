package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolEvaluationAutoTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolEvaluationAuto =
            BetaManagedAgentsAgentToolEvaluationAuto.builder()
                .evaluatedPermission(
                    BetaManagedAgentsAgentAutoEvaluatedPermissionAllow.builder().build()
                )
                .build()

        assertThat(betaManagedAgentsAgentToolEvaluationAuto.evaluatedPermission())
            .isEqualTo(
                BetaManagedAgentsAgentAutoEvaluatedPermission.ofAllow(
                    BetaManagedAgentsAgentAutoEvaluatedPermissionAllow.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolEvaluationAuto =
            BetaManagedAgentsAgentToolEvaluationAuto.builder()
                .evaluatedPermission(
                    BetaManagedAgentsAgentAutoEvaluatedPermissionAllow.builder().build()
                )
                .build()

        val roundtrippedBetaManagedAgentsAgentToolEvaluationAuto =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolEvaluationAuto),
                jacksonTypeRef<BetaManagedAgentsAgentToolEvaluationAuto>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolEvaluationAuto)
            .isEqualTo(betaManagedAgentsAgentToolEvaluationAuto)
    }
}
