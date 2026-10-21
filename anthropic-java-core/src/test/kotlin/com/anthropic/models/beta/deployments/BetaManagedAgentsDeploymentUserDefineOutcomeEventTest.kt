package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
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
                .maxIterations(0)
                .build()

        assertThat(betaManagedAgentsDeploymentUserDefineOutcomeEvent.description())
            .isEqualTo("description")
        assertThat(betaManagedAgentsDeploymentUserDefineOutcomeEvent.rubric())
            .isEqualTo(BetaManagedAgentsDeploymentUserDefineOutcomeEvent.Rubric.ofFile("file_id"))
        assertThat(betaManagedAgentsDeploymentUserDefineOutcomeEvent.maxIterations()).contains(0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentUserDefineOutcomeEvent =
            BetaManagedAgentsDeploymentUserDefineOutcomeEvent.builder()
                .description("description")
                .fileRubric("file_id")
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
