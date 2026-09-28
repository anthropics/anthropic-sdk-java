package com.anthropic.models.beta.organization.plugins.installationsettings

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.plugins.BetaPluginTargetOrganization
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InstallationSettingListPageResponseTest {

    @Test
    fun create() {
        val installationSettingListPageResponse =
            InstallationSettingListPageResponse.builder()
                .addData(
                    BetaPluginInstallationSetting.builder()
                        .createdAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                        .installationPreference(
                            BetaPluginInstallationSetting.InstallationPreference.REQUIRED
                        )
                        .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                        .target(BetaPluginTargetOrganization.builder().build())
                        .updatedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                        .build()
                )
                .nextPage("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
                .build()

        assertThat(installationSettingListPageResponse.data())
            .containsExactly(
                BetaPluginInstallationSetting.builder()
                    .createdAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                    .installationPreference(
                        BetaPluginInstallationSetting.InstallationPreference.REQUIRED
                    )
                    .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                    .target(BetaPluginTargetOrganization.builder().build())
                    .updatedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                    .build()
            )
        assertThat(installationSettingListPageResponse.nextPage())
            .contains("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val installationSettingListPageResponse =
            InstallationSettingListPageResponse.builder()
                .addData(
                    BetaPluginInstallationSetting.builder()
                        .createdAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                        .installationPreference(
                            BetaPluginInstallationSetting.InstallationPreference.REQUIRED
                        )
                        .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                        .target(BetaPluginTargetOrganization.builder().build())
                        .updatedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                        .build()
                )
                .nextPage("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
                .build()

        val roundtrippedInstallationSettingListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(installationSettingListPageResponse),
                jacksonTypeRef<InstallationSettingListPageResponse>(),
            )

        assertThat(roundtrippedInstallationSettingListPageResponse)
            .isEqualTo(installationSettingListPageResponse)
    }
}
