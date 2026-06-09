// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeploymentRunTest {

    @Test
    fun create() {
        val betaManagedAgentsDeploymentRun =
            BetaManagedAgentsDeploymentRun.builder()
                .id("id")
                .agent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .deploymentId("deployment_id")
                .environmentArchivedError("message")
                .sessionId("session_id")
                .scheduleTriggerContext(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsDeploymentRun.Type.DEPLOYMENT_RUN)
                .build()

        assertThat(betaManagedAgentsDeploymentRun.id()).isEqualTo("id")
        assertThat(betaManagedAgentsDeploymentRun.agent())
            .isEqualTo(
                BetaManagedAgentsAgentReference.builder()
                    .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                    .type(BetaManagedAgentsAgentReference.Type.AGENT)
                    .version(1)
                    .build()
            )
        assertThat(betaManagedAgentsDeploymentRun.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsDeploymentRun.deploymentId()).isEqualTo("deployment_id")
        assertThat(betaManagedAgentsDeploymentRun.error())
            .contains(
                BetaManagedAgentsDeploymentRun.Error.ofEnvironmentArchived(
                    BetaManagedAgentsEnvironmentArchivedRunError.builder()
                        .message("message")
                        .type(
                            BetaManagedAgentsEnvironmentArchivedRunError.Type
                                .ENVIRONMENT_ARCHIVED_ERROR
                        )
                        .build()
                )
            )
        assertThat(betaManagedAgentsDeploymentRun.sessionId()).contains("session_id")
        assertThat(betaManagedAgentsDeploymentRun.triggerContext())
            .isEqualTo(
                BetaManagedAgentsTriggerContext.ofSchedule(
                    BetaManagedAgentsScheduleTriggerContext.builder()
                        .scheduledAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .type(BetaManagedAgentsScheduleTriggerContext.Type.SCHEDULE)
                        .build()
                )
            )
        assertThat(betaManagedAgentsDeploymentRun.type())
            .isEqualTo(BetaManagedAgentsDeploymentRun.Type.DEPLOYMENT_RUN)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentRun =
            BetaManagedAgentsDeploymentRun.builder()
                .id("id")
                .agent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .deploymentId("deployment_id")
                .environmentArchivedError("message")
                .sessionId("session_id")
                .scheduleTriggerContext(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsDeploymentRun.Type.DEPLOYMENT_RUN)
                .build()

        val roundtrippedBetaManagedAgentsDeploymentRun =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentRun),
                jacksonTypeRef<BetaManagedAgentsDeploymentRun>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentRun)
            .isEqualTo(betaManagedAgentsDeploymentRun)
    }
}
