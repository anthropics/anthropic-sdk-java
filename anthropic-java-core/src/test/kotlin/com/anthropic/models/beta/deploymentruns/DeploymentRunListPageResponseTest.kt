// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeploymentRunListPageResponseTest {

    @Test
    fun create() {
        val deploymentRunListPageResponse =
            DeploymentRunListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(deploymentRunListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(deploymentRunListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val deploymentRunListPageResponse =
            DeploymentRunListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedDeploymentRunListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(deploymentRunListPageResponse),
                jacksonTypeRef<DeploymentRunListPageResponse>(),
            )

        assertThat(roundtrippedDeploymentRunListPageResponse)
            .isEqualTo(deploymentRunListPageResponse)
    }
}
