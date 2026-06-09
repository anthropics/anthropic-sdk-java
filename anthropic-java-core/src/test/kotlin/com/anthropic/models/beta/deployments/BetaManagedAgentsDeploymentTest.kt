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

internal class BetaManagedAgentsDeploymentTest {

    @Test
    fun create() {
        val betaManagedAgentsDeployment =
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
                            BetaManagedAgentsGitHubRepositoryResourceConfig.Type.GITHUB_REPOSITORY
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

        assertThat(betaManagedAgentsDeployment.id()).isEqualTo("id")
        assertThat(betaManagedAgentsDeployment.agent())
            .isEqualTo(
                BetaManagedAgentsAgentReference.builder()
                    .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                    .type(BetaManagedAgentsAgentReference.Type.AGENT)
                    .version(1)
                    .build()
            )
        assertThat(betaManagedAgentsDeployment.archivedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsDeployment.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsDeployment.description()).contains("description")
        assertThat(betaManagedAgentsDeployment.environmentId()).isEqualTo("environment_id")
        assertThat(betaManagedAgentsDeployment.initialEvents())
            .containsExactly(
                BetaManagedAgentsDeploymentInitialEvent.ofUserMessage(
                    BetaManagedAgentsDeploymentUserMessageEvent.builder()
                        .addTextContent("Where is my order #1234?")
                        .type(BetaManagedAgentsDeploymentUserMessageEvent.Type.USER_MESSAGE)
                        .build()
                )
            )
        assertThat(betaManagedAgentsDeployment.metadata())
            .isEqualTo(
                BetaManagedAgentsDeployment.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(betaManagedAgentsDeployment.name()).isEqualTo("name")
        assertThat(betaManagedAgentsDeployment.pausedReason())
            .contains(
                BetaManagedAgentsDeploymentPausedReason.ofManual(
                    BetaManagedAgentsManualDeploymentPausedReason.builder()
                        .type(BetaManagedAgentsManualDeploymentPausedReason.Type.MANUAL)
                        .build()
                )
            )
        assertThat(betaManagedAgentsDeployment.resources())
            .containsExactly(
                BetaManagedAgentsSessionResourceConfig.ofGitHubRepository(
                    BetaManagedAgentsGitHubRepositoryResourceConfig.builder()
                        .type(
                            BetaManagedAgentsGitHubRepositoryResourceConfig.Type.GITHUB_REPOSITORY
                        )
                        .url("url")
                        .branchCheckout("main")
                        .mountPath("mount_path")
                        .build()
                )
            )
        assertThat(betaManagedAgentsDeployment.schedule())
            .contains(
                BetaManagedAgentsSchedule.builder()
                    .expression("x")
                    .timezone("x")
                    .type(BetaManagedAgentsSchedule.Type.CRON)
                    .lastRunAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .addUpcomingRunsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )
        assertThat(betaManagedAgentsDeployment.status())
            .isEqualTo(BetaManagedAgentsDeploymentStatus.ACTIVE)
        assertThat(betaManagedAgentsDeployment.type())
            .isEqualTo(BetaManagedAgentsDeployment.Type.DEPLOYMENT)
        assertThat(betaManagedAgentsDeployment.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsDeployment.vaultIds()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeployment =
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
                            BetaManagedAgentsGitHubRepositoryResourceConfig.Type.GITHUB_REPOSITORY
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

        val roundtrippedBetaManagedAgentsDeployment =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeployment),
                jacksonTypeRef<BetaManagedAgentsDeployment>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeployment).isEqualTo(betaManagedAgentsDeployment)
    }
}
