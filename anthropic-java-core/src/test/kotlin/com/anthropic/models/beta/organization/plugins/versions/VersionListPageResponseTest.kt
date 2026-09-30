package com.anthropic.models.beta.organization.plugins.versions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.plugins.BetaPluginComponent
import com.anthropic.models.beta.organization.plugins.BetaPluginContentScan
import com.anthropic.models.beta.organization.plugins.BetaPluginUserActor
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionListPageResponseTest {

    @Test
    fun create() {
        val versionListPageResponse =
            VersionListPageResponse.builder()
                .addData(
                    BetaPluginVersion.builder()
                        .id("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
                        .addComponent(
                            BetaPluginComponent.builder()
                                .description("description")
                                .name("review-pr")
                                .type(BetaPluginComponent.Type.SKILL)
                                .build()
                        )
                        .contentScan(
                            BetaPluginContentScan.builder()
                                .assessment(BetaPluginContentScan.Assessment.WARN)
                                .reason("credential-exposure")
                                .status(BetaPluginContentScan.Status.COMPLETED)
                                .build()
                        )
                        .createdAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                        .createdBy(
                            BetaPluginUserActor.builder()
                                .emailAddress("user@example.com")
                                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .build()
                        )
                        .description("Reviews pull requests against your team's conventions.")
                        .displayName("Code Review Helper")
                        .manifestVersion("1.2.0")
                        .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                        .reach(BetaPluginVersion.Reach.CONTAINED)
                        .releaseNotes("Adds a review checklist for database migrations.")
                        .build()
                )
                .nextPage("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
                .build()

        assertThat(versionListPageResponse.data())
            .containsExactly(
                BetaPluginVersion.builder()
                    .id("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
                    .addComponent(
                        BetaPluginComponent.builder()
                            .description("description")
                            .name("review-pr")
                            .type(BetaPluginComponent.Type.SKILL)
                            .build()
                    )
                    .contentScan(
                        BetaPluginContentScan.builder()
                            .assessment(BetaPluginContentScan.Assessment.WARN)
                            .reason("credential-exposure")
                            .status(BetaPluginContentScan.Status.COMPLETED)
                            .build()
                    )
                    .createdAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                    .createdBy(
                        BetaPluginUserActor.builder()
                            .emailAddress("user@example.com")
                            .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                            .build()
                    )
                    .description("Reviews pull requests against your team's conventions.")
                    .displayName("Code Review Helper")
                    .manifestVersion("1.2.0")
                    .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                    .reach(BetaPluginVersion.Reach.CONTAINED)
                    .releaseNotes("Adds a review checklist for database migrations.")
                    .build()
            )
        assertThat(versionListPageResponse.nextPage()).contains("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val versionListPageResponse =
            VersionListPageResponse.builder()
                .addData(
                    BetaPluginVersion.builder()
                        .id("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
                        .addComponent(
                            BetaPluginComponent.builder()
                                .description("description")
                                .name("review-pr")
                                .type(BetaPluginComponent.Type.SKILL)
                                .build()
                        )
                        .contentScan(
                            BetaPluginContentScan.builder()
                                .assessment(BetaPluginContentScan.Assessment.WARN)
                                .reason("credential-exposure")
                                .status(BetaPluginContentScan.Status.COMPLETED)
                                .build()
                        )
                        .createdAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                        .createdBy(
                            BetaPluginUserActor.builder()
                                .emailAddress("user@example.com")
                                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                                .build()
                        )
                        .description("Reviews pull requests against your team's conventions.")
                        .displayName("Code Review Helper")
                        .manifestVersion("1.2.0")
                        .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                        .reach(BetaPluginVersion.Reach.CONTAINED)
                        .releaseNotes("Adds a review checklist for database migrations.")
                        .build()
                )
                .nextPage("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
                .build()

        val roundtrippedVersionListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(versionListPageResponse),
                jacksonTypeRef<VersionListPageResponse>(),
            )

        assertThat(roundtrippedVersionListPageResponse).isEqualTo(versionListPageResponse)
    }
}
