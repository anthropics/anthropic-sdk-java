package com.anthropic.models.beta.organization.plugins.installationsettings

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.plugins.BetaPluginTargetOrganization
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginInstallationSettingTest {

    @Test
    fun create() {
        val betaPluginInstallationSetting =
            BetaPluginInstallationSetting.builder()
                .createdAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                .installationPreference(
                    BetaPluginInstallationSetting.InstallationPreference.REQUIRED
                )
                .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                .target(BetaPluginTargetOrganization.builder().build())
                .updatedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                .build()

        assertThat(betaPluginInstallationSetting.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
        assertThat(betaPluginInstallationSetting.installationPreference())
            .isEqualTo(BetaPluginInstallationSetting.InstallationPreference.REQUIRED)
        assertThat(betaPluginInstallationSetting.pluginId())
            .isEqualTo("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
        assertThat(betaPluginInstallationSetting.target())
            .isEqualTo(
                BetaPluginInstallationSetting.Target.ofOrganization(
                    BetaPluginTargetOrganization.builder().build()
                )
            )
        assertThat(betaPluginInstallationSetting.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginInstallationSetting =
            BetaPluginInstallationSetting.builder()
                .createdAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                .installationPreference(
                    BetaPluginInstallationSetting.InstallationPreference.REQUIRED
                )
                .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                .target(BetaPluginTargetOrganization.builder().build())
                .updatedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                .build()

        val roundtrippedBetaPluginInstallationSetting =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginInstallationSetting),
                jacksonTypeRef<BetaPluginInstallationSetting>(),
            )

        assertThat(roundtrippedBetaPluginInstallationSetting)
            .isEqualTo(betaPluginInstallationSetting)
    }
}
