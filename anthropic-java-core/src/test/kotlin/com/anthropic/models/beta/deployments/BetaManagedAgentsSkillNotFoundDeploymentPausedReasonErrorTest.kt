package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSkillNotFoundDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSkillNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSkillNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError.builder().build()

        val roundtrippedBetaManagedAgentsSkillNotFoundDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsSkillNotFoundDeploymentPausedReasonError
                ),
                jacksonTypeRef<BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSkillNotFoundDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsSkillNotFoundDeploymentPausedReasonError)
    }
}
