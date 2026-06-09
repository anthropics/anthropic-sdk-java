// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSkillNotFoundDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSkillNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError.Type
                        .SKILL_NOT_FOUND_ERROR
                )
                .build()

        assertThat(betaManagedAgentsSkillNotFoundDeploymentPausedReasonError.type())
            .isEqualTo(
                BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError.Type.SKILL_NOT_FOUND_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSkillNotFoundDeploymentPausedReasonError =
            BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError.Type
                        .SKILL_NOT_FOUND_ERROR
                )
                .build()

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
