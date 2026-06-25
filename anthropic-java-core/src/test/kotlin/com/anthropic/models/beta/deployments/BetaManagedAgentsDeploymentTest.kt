// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

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
                .id("depl_011CZkZcDH3vPqd7xnEfwTai")
                .agent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYpogX7uDKUyvBTophP")
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .archivedAt(null)
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .description("Compiles yesterday's orders into a report every weekday morning.")
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .addUserMessageInitialEvent(
                    listOf(
                        BetaManagedAgentsDeploymentUserMessageEvent.Content.ofText(
                            BetaManagedAgentsTextBlock.builder()
                                .text("Compile yesterday's orders into report.md.")
                                .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                .build()
                        )
                    )
                )
                .metadata(BetaManagedAgentsDeployment.Metadata.builder().build())
                .name("Daily order report")
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
                        .expression("0 9 * * 1-5")
                        .timezone("America/Los_Angeles")
                        .type(BetaManagedAgentsSchedule.Type.CRON)
                        .lastRunAt(OffsetDateTime.parse("2026-03-16T16:00:09Z"))
                        .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-17T16:00:00Z"))
                        .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-18T16:00:00Z"))
                        .build()
                )
                .status(BetaManagedAgentsDeploymentStatus.ACTIVE)
                .type(BetaManagedAgentsDeployment.Type.DEPLOYMENT)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .addVaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .build()

        assertThat(betaManagedAgentsDeployment.id()).isEqualTo("depl_011CZkZcDH3vPqd7xnEfwTai")
        assertThat(betaManagedAgentsDeployment.agent())
            .isEqualTo(
                BetaManagedAgentsAgentReference.builder()
                    .id("agent_011CZkYpogX7uDKUyvBTophP")
                    .type(BetaManagedAgentsAgentReference.Type.AGENT)
                    .version(1)
                    .build()
            )
        assertThat(betaManagedAgentsDeployment.archivedAt()).isEmpty
        assertThat(betaManagedAgentsDeployment.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaManagedAgentsDeployment.description())
            .contains("Compiles yesterday's orders into a report every weekday morning.")
        assertThat(betaManagedAgentsDeployment.environmentId())
            .isEqualTo("env_011CZkZ9X2dpNyB7HsEFoRfW")
        assertThat(betaManagedAgentsDeployment.initialEvents())
            .containsExactly(
                BetaManagedAgentsDeploymentInitialEvent.ofUserMessage(
                    BetaManagedAgentsDeploymentUserMessageEvent.builder()
                        .addTextContent("Compile yesterday's orders into report.md.")
                        .type(BetaManagedAgentsDeploymentUserMessageEvent.Type.USER_MESSAGE)
                        .build()
                )
            )
        assertThat(betaManagedAgentsDeployment.metadata())
            .isEqualTo(BetaManagedAgentsDeployment.Metadata.builder().build())
        assertThat(betaManagedAgentsDeployment.name()).isEqualTo("Daily order report")
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
                    .expression("0 9 * * 1-5")
                    .timezone("America/Los_Angeles")
                    .type(BetaManagedAgentsSchedule.Type.CRON)
                    .lastRunAt(OffsetDateTime.parse("2026-03-16T16:00:09Z"))
                    .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-17T16:00:00Z"))
                    .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-18T16:00:00Z"))
                    .build()
            )
        assertThat(betaManagedAgentsDeployment.status())
            .isEqualTo(BetaManagedAgentsDeploymentStatus.ACTIVE)
        assertThat(betaManagedAgentsDeployment.type())
            .isEqualTo(BetaManagedAgentsDeployment.Type.DEPLOYMENT)
        assertThat(betaManagedAgentsDeployment.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaManagedAgentsDeployment.vaultIds())
            .containsExactly("vlt_011CZkZDLs7fYzm1hXNPeRjv")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeployment =
            BetaManagedAgentsDeployment.builder()
                .id("depl_011CZkZcDH3vPqd7xnEfwTai")
                .agent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYpogX7uDKUyvBTophP")
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .archivedAt(null)
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .description("Compiles yesterday's orders into a report every weekday morning.")
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .addUserMessageInitialEvent(
                    listOf(
                        BetaManagedAgentsDeploymentUserMessageEvent.Content.ofText(
                            BetaManagedAgentsTextBlock.builder()
                                .text("Compile yesterday's orders into report.md.")
                                .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                .build()
                        )
                    )
                )
                .metadata(BetaManagedAgentsDeployment.Metadata.builder().build())
                .name("Daily order report")
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
                        .expression("0 9 * * 1-5")
                        .timezone("America/Los_Angeles")
                        .type(BetaManagedAgentsSchedule.Type.CRON)
                        .lastRunAt(OffsetDateTime.parse("2026-03-16T16:00:09Z"))
                        .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-17T16:00:00Z"))
                        .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-18T16:00:00Z"))
                        .build()
                )
                .status(BetaManagedAgentsDeploymentStatus.ACTIVE)
                .type(BetaManagedAgentsDeployment.Type.DEPLOYMENT)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .addVaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                .build()

        val roundtrippedBetaManagedAgentsDeployment =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeployment),
                jacksonTypeRef<BetaManagedAgentsDeployment>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeployment).isEqualTo(betaManagedAgentsDeployment)
    }
}
