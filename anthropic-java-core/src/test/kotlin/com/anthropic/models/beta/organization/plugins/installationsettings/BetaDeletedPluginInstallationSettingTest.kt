package com.anthropic.models.beta.organization.plugins.installationsettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedPluginInstallationSettingTest {

    @Test
    fun create() {
        val betaDeletedPluginInstallationSetting =
            BetaDeletedPluginInstallationSetting.builder()
                .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                .rbacGroupTarget("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .build()

        assertThat(betaDeletedPluginInstallationSetting.pluginId())
            .isEqualTo("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
        assertThat(betaDeletedPluginInstallationSetting.target())
            .isEqualTo(
                BetaDeletedPluginInstallationSetting.Target.ofRbacGroup(
                    "rbac_group_012rppKaSVsmTo6NqRDXQXNF"
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedPluginInstallationSetting =
            BetaDeletedPluginInstallationSetting.builder()
                .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                .rbacGroupTarget("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .build()

        val roundtrippedBetaDeletedPluginInstallationSetting =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedPluginInstallationSetting),
                jacksonTypeRef<BetaDeletedPluginInstallationSetting>(),
            )

        assertThat(roundtrippedBetaDeletedPluginInstallationSetting)
            .isEqualTo(betaDeletedPluginInstallationSetting)
    }
}
