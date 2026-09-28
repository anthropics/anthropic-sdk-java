package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PluginListPageResponseTest {

    @Test
    fun create() {
        val pluginListPageResponse =
            PluginListPageResponse.builder()
                .addData(
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
                )
                .nextPage("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
                .build()

        assertThat(pluginListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(pluginListPageResponse.nextPage()).contains("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val pluginListPageResponse =
            PluginListPageResponse.builder()
                .addData(
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
                )
                .nextPage("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
                .build()

        val roundtrippedPluginListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(pluginListPageResponse),
                jacksonTypeRef<PluginListPageResponse>(),
            )

        assertThat(roundtrippedPluginListPageResponse).isEqualTo(pluginListPageResponse)
    }
}
