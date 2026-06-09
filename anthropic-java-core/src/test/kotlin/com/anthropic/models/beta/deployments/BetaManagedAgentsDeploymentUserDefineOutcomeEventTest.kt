// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsFileRubric
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeploymentUserDefineOutcomeEventTest {

    @Test
    fun create() {
        val betaManagedAgentsDeploymentUserDefineOutcomeEvent =
            BetaManagedAgentsDeploymentUserDefineOutcomeEvent.builder()
                .description("description")
                .fileRubric("file_id")
                .type(BetaManagedAgentsDeploymentUserDefineOutcomeEvent.Type.USER_DEFINE_OUTCOME)
                .maxIterations(0)
                .build()

        assertThat(betaManagedAgentsDeploymentUserDefineOutcomeEvent.description())
            .isEqualTo("description")
        assertThat(betaManagedAgentsDeploymentUserDefineOutcomeEvent.rubric())
            .isEqualTo(
                BetaManagedAgentsDeploymentUserDefineOutcomeEvent.Rubric.ofFile(
                    BetaManagedAgentsFileRubric.builder()
                        .fileId("file_id")
                        .type(BetaManagedAgentsFileRubric.Type.FILE)
                        .build()
                )
            )
        assertThat(betaManagedAgentsDeploymentUserDefineOutcomeEvent.type())
            .isEqualTo(BetaManagedAgentsDeploymentUserDefineOutcomeEvent.Type.USER_DEFINE_OUTCOME)
        assertThat(betaManagedAgentsDeploymentUserDefineOutcomeEvent.maxIterations()).contains(0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentUserDefineOutcomeEvent =
            BetaManagedAgentsDeploymentUserDefineOutcomeEvent.builder()
                .description("description")
                .fileRubric("file_id")
                .type(BetaManagedAgentsDeploymentUserDefineOutcomeEvent.Type.USER_DEFINE_OUTCOME)
                .maxIterations(0)
                .build()

        val roundtrippedBetaManagedAgentsDeploymentUserDefineOutcomeEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentUserDefineOutcomeEvent),
                jacksonTypeRef<BetaManagedAgentsDeploymentUserDefineOutcomeEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentUserDefineOutcomeEvent)
            .isEqualTo(betaManagedAgentsDeploymentUserDefineOutcomeEvent)
    }
}
