package com.anthropic.models.beta.organization.plugins.versions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.plugins.BetaPluginComponent
import com.anthropic.models.beta.organization.plugins.BetaPluginContentScan
import com.anthropic.models.beta.organization.plugins.BetaPluginUserActor
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginVersionTest {

    @Test
    fun create() {
        val betaPluginVersion =
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

        assertThat(betaPluginVersion.id()).isEqualTo("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
        assertThat(betaPluginVersion.components().getOrNull())
            .containsExactly(
                BetaPluginComponent.builder()
                    .description("description")
                    .name("review-pr")
                    .type(BetaPluginComponent.Type.SKILL)
                    .build()
            )
        assertThat(betaPluginVersion.contentScan())
            .contains(
                BetaPluginContentScan.builder()
                    .assessment(BetaPluginContentScan.Assessment.WARN)
                    .reason("credential-exposure")
                    .status(BetaPluginContentScan.Status.COMPLETED)
                    .build()
            )
        assertThat(betaPluginVersion.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
        assertThat(betaPluginVersion.createdBy())
            .contains(
                BetaPluginVersion.CreatedBy.ofUserActor(
                    BetaPluginUserActor.builder()
                        .emailAddress("user@example.com")
                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .build()
                )
            )
        assertThat(betaPluginVersion.description())
            .contains("Reviews pull requests against your team's conventions.")
        assertThat(betaPluginVersion.displayName()).contains("Code Review Helper")
        assertThat(betaPluginVersion.manifestVersion()).contains("1.2.0")
        assertThat(betaPluginVersion.pluginId()).isEqualTo("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
        assertThat(betaPluginVersion.reach()).contains(BetaPluginVersion.Reach.CONTAINED)
        assertThat(betaPluginVersion.releaseNotes())
            .contains("Adds a review checklist for database migrations.")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginVersion =
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

        val roundtrippedBetaPluginVersion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginVersion),
                jacksonTypeRef<BetaPluginVersion>(),
            )

        assertThat(roundtrippedBetaPluginVersion).isEqualTo(betaPluginVersion)
    }
}
