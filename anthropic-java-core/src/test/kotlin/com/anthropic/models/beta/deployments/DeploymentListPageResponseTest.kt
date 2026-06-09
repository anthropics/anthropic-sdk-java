// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentReference
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeploymentListPageResponseTest {

    @Test
    fun create() {
        val deploymentListPageResponse =
            DeploymentListPageResponse.builder()
                .addData(
                    BetaManagedAgentsDeployment.builder()
                        .id("id")
                        .agent(
                            BetaManagedAgentsAgentReference.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .type(BetaManagedAgentsAgentReference.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .description("description")
                        .environmentId("environment_id")
                        .addUserMessageInitialEvent(
                            listOf(
                                BetaManagedAgentsDeploymentUserMessageEvent.Content.ofText(
                                    BetaManagedAgentsTextBlock.builder()
                                        .text("Where is my order #1234?")
                                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                        .build()
                                )
                            )
                        )
                        .metadata(
                            BetaManagedAgentsDeployment.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .name("name")
                        .pausedReason(
                            BetaManagedAgentsManualDeploymentPausedReason.builder()
                                .type(BetaManagedAgentsManualDeploymentPausedReason.Type.MANUAL)
                                .build()
                        )
                        .addResource(
                            BetaManagedAgentsGitHubRepositoryResourceConfig.builder()
                                .type(
                                    BetaManagedAgentsGitHubRepositoryResourceConfig.Type
                                        .GITHUB_REPOSITORY
                                )
                                .url("url")
                                .branchCheckout("main")
                                .mountPath("mount_path")
                                .build()
                        )
                        .schedule(
                            BetaManagedAgentsSchedule.builder()
                                .expression("x")
                                .timezone("x")
                                .type(BetaManagedAgentsSchedule.Type.CRON)
                                .lastRunAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .addUpcomingRunsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .build()
                        )
                        .status(BetaManagedAgentsDeploymentStatus.ACTIVE)
                        .type(BetaManagedAgentsDeployment.Type.DEPLOYMENT)
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .addVaultId("string")
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(deploymentListPageResponse.data())
            .containsExactly(
                BetaManagedAgentsDeployment.builder()
                    .id("id")
                    .agent(
                        BetaManagedAgentsAgentReference.builder()
                            .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                            .type(BetaManagedAgentsAgentReference.Type.AGENT)
                            .version(1)
                            .build()
                    )
                    .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .description("description")
                    .environmentId("environment_id")
                    .addUserMessageInitialEvent(
                        listOf(
                            BetaManagedAgentsDeploymentUserMessageEvent.Content.ofText(
                                BetaManagedAgentsTextBlock.builder()
                                    .text("Where is my order #1234?")
                                    .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                    .build()
                            )
                        )
                    )
                    .metadata(
                        BetaManagedAgentsDeployment.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .name("name")
                    .pausedReason(
                        BetaManagedAgentsManualDeploymentPausedReason.builder()
                            .type(BetaManagedAgentsManualDeploymentPausedReason.Type.MANUAL)
                            .build()
                    )
                    .addResource(
                        BetaManagedAgentsGitHubRepositoryResourceConfig.builder()
                            .type(
                                BetaManagedAgentsGitHubRepositoryResourceConfig.Type
                                    .GITHUB_REPOSITORY
                            )
                            .url("url")
                            .branchCheckout("main")
                            .mountPath("mount_path")
                            .build()
                    )
                    .schedule(
                        BetaManagedAgentsSchedule.builder()
                            .expression("x")
                            .timezone("x")
                            .type(BetaManagedAgentsSchedule.Type.CRON)
                            .lastRunAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                            .addUpcomingRunsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                            .build()
                    )
                    .status(BetaManagedAgentsDeploymentStatus.ACTIVE)
                    .type(BetaManagedAgentsDeployment.Type.DEPLOYMENT)
                    .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .addVaultId("string")
                    .build()
            )
        assertThat(deploymentListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val deploymentListPageResponse =
            DeploymentListPageResponse.builder()
                .addData(
                    BetaManagedAgentsDeployment.builder()
                        .id("id")
                        .agent(
                            BetaManagedAgentsAgentReference.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .type(BetaManagedAgentsAgentReference.Type.AGENT)
                                .version(1)
                                .build()
                        )
                        .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .description("description")
                        .environmentId("environment_id")
                        .addUserMessageInitialEvent(
                            listOf(
                                BetaManagedAgentsDeploymentUserMessageEvent.Content.ofText(
                                    BetaManagedAgentsTextBlock.builder()
                                        .text("Where is my order #1234?")
                                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                        .build()
                                )
                            )
                        )
                        .metadata(
                            BetaManagedAgentsDeployment.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .name("name")
                        .pausedReason(
                            BetaManagedAgentsManualDeploymentPausedReason.builder()
                                .type(BetaManagedAgentsManualDeploymentPausedReason.Type.MANUAL)
                                .build()
                        )
                        .addResource(
                            BetaManagedAgentsGitHubRepositoryResourceConfig.builder()
                                .type(
                                    BetaManagedAgentsGitHubRepositoryResourceConfig.Type
                                        .GITHUB_REPOSITORY
                                )
                                .url("url")
                                .branchCheckout("main")
                                .mountPath("mount_path")
                                .build()
                        )
                        .schedule(
                            BetaManagedAgentsSchedule.builder()
                                .expression("x")
                                .timezone("x")
                                .type(BetaManagedAgentsSchedule.Type.CRON)
                                .lastRunAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .addUpcomingRunsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .build()
                        )
                        .status(BetaManagedAgentsDeploymentStatus.ACTIVE)
                        .type(BetaManagedAgentsDeployment.Type.DEPLOYMENT)
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .addVaultId("string")
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedDeploymentListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(deploymentListPageResponse),
                jacksonTypeRef<DeploymentListPageResponse>(),
            )

        assertThat(roundtrippedDeploymentListPageResponse).isEqualTo(deploymentListPageResponse)
    }
}
