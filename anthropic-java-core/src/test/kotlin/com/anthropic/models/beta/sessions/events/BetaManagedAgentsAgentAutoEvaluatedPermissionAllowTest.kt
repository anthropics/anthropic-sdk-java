package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentAutoEvaluatedPermissionAllowTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentAutoEvaluatedPermissionAllow =
            BetaManagedAgentsAgentAutoEvaluatedPermissionAllow.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentAutoEvaluatedPermissionAllow =
            BetaManagedAgentsAgentAutoEvaluatedPermissionAllow.builder().build()

        val roundtrippedBetaManagedAgentsAgentAutoEvaluatedPermissionAllow =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentAutoEvaluatedPermissionAllow),
                jacksonTypeRef<BetaManagedAgentsAgentAutoEvaluatedPermissionAllow>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentAutoEvaluatedPermissionAllow)
            .isEqualTo(betaManagedAgentsAgentAutoEvaluatedPermissionAllow)
    }
}
