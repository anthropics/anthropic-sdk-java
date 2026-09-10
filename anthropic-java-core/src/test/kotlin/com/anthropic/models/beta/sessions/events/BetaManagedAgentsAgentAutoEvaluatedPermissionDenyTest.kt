package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentAutoEvaluatedPermissionDenyTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentAutoEvaluatedPermissionDeny =
            BetaManagedAgentsAgentAutoEvaluatedPermissionDeny.of("reason_code")

        assertThat(betaManagedAgentsAgentAutoEvaluatedPermissionDeny.reasonCode())
            .isEqualTo("reason_code")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentAutoEvaluatedPermissionDeny =
            BetaManagedAgentsAgentAutoEvaluatedPermissionDeny.of("reason_code")

        val roundtrippedBetaManagedAgentsAgentAutoEvaluatedPermissionDeny =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentAutoEvaluatedPermissionDeny),
                jacksonTypeRef<BetaManagedAgentsAgentAutoEvaluatedPermissionDeny>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentAutoEvaluatedPermissionDeny)
            .isEqualTo(betaManagedAgentsAgentAutoEvaluatedPermissionDeny)
    }
}
