package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginTest {

    @Test
    fun create() {
        val betaPlugin =
            BetaPlugin.builder()
                .id("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
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
                .latestVersionId("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
                .manifestVersion("1.2.0")
                .marketplaceId("marketplace_01HxQ3v9KpZ2mTn8RwLc4Ys7")
                .name("code-review-helper")
                .organizationInstallationPreference(
                    BetaPlugin.OrganizationInstallationPreference.AVAILABLE
                )
                .organizationInstallationPreferenceInherited(true)
                .owner(BetaPluginOwnerOrganization.builder().build())
                .reach(BetaPlugin.Reach.CONTAINED)
                .servedVersionId("pluginver_01K9wPcHd4Rm2Tx8Vq6Ln3Sb")
                .servedVersionPinned(true)
                .updatedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                .build()

        assertThat(betaPlugin.id()).isEqualTo("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
        assertThat(betaPlugin.components().getOrNull())
            .containsExactly(
                BetaPluginComponent.builder()
                    .description("description")
                    .name("review-pr")
                    .type(BetaPluginComponent.Type.SKILL)
                    .build()
            )
        assertThat(betaPlugin.contentScan())
            .contains(
                BetaPluginContentScan.builder()
                    .assessment(BetaPluginContentScan.Assessment.WARN)
                    .reason("credential-exposure")
                    .status(BetaPluginContentScan.Status.COMPLETED)
                    .build()
            )
        assertThat(betaPlugin.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
        assertThat(betaPlugin.createdBy())
            .contains(
                BetaPlugin.CreatedBy.ofUserActor(
                    BetaPluginUserActor.builder()
                        .emailAddress("user@example.com")
                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .build()
                )
            )
        assertThat(betaPlugin.description())
            .contains("Reviews pull requests against your team's conventions.")
        assertThat(betaPlugin.displayName()).contains("Code Review Helper")
        assertThat(betaPlugin.latestVersionId()).isEqualTo("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
        assertThat(betaPlugin.manifestVersion()).contains("1.2.0")
        assertThat(betaPlugin.marketplaceId()).isEqualTo("marketplace_01HxQ3v9KpZ2mTn8RwLc4Ys7")
        assertThat(betaPlugin.name()).isEqualTo("code-review-helper")
        assertThat(betaPlugin.organizationInstallationPreference())
            .contains(BetaPlugin.OrganizationInstallationPreference.AVAILABLE)
        assertThat(betaPlugin.organizationInstallationPreferenceInherited()).contains(true)
        assertThat(betaPlugin.owner())
            .isEqualTo(
                BetaPlugin.Owner.ofOrganization(BetaPluginOwnerOrganization.builder().build())
            )
        assertThat(betaPlugin.reach()).contains(BetaPlugin.Reach.CONTAINED)
        assertThat(betaPlugin.servedVersionId()).isEqualTo("pluginver_01K9wPcHd4Rm2Tx8Vq6Ln3Sb")
        assertThat(betaPlugin.servedVersionPinned()).isEqualTo(true)
        assertThat(betaPlugin.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPlugin =
            BetaPlugin.builder()
                .id("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
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
                .latestVersionId("pluginver_01KaZmQpRsTuVwXyZ2b4c6d8")
                .manifestVersion("1.2.0")
                .marketplaceId("marketplace_01HxQ3v9KpZ2mTn8RwLc4Ys7")
                .name("code-review-helper")
                .organizationInstallationPreference(
                    BetaPlugin.OrganizationInstallationPreference.AVAILABLE
                )
                .organizationInstallationPreferenceInherited(true)
                .owner(BetaPluginOwnerOrganization.builder().build())
                .reach(BetaPlugin.Reach.CONTAINED)
                .servedVersionId("pluginver_01K9wPcHd4Rm2Tx8Vq6Ln3Sb")
                .servedVersionPinned(true)
                .updatedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                .build()

        val roundtrippedBetaPlugin =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPlugin),
                jacksonTypeRef<BetaPlugin>(),
            )

        assertThat(roundtrippedBetaPlugin).isEqualTo(betaPlugin)
    }
}
