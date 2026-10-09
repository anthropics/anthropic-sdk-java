package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsPluginActivityTest {

    @Test
    fun create() {
        val betaAnalyticsPluginActivity =
            BetaAnalyticsPluginActivity.builder()
                .claudeCodeMetrics(BetaAnalyticsPluginClaudeCodeMetrics.of(0L))
                .coworkMetrics(BetaAnalyticsPluginCoworkMetrics.of(0L))
                .distinctUserCount(0L)
                .installCount(0L)
                .invocationCount(0L)
                .pluginName("plugin_name")
                .chatCoworkUnifiedMetrics(
                    BetaAnalyticsPluginActivity.ChatCoworkUnifiedMetrics.of(0L)
                )
                .pluginId("plugin_id")
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        assertThat(betaAnalyticsPluginActivity.claudeCodeMetrics())
            .isEqualTo(BetaAnalyticsPluginClaudeCodeMetrics.of(0L))
        assertThat(betaAnalyticsPluginActivity.coworkMetrics())
            .isEqualTo(BetaAnalyticsPluginCoworkMetrics.of(0L))
        assertThat(betaAnalyticsPluginActivity.distinctUserCount()).isEqualTo(0L)
        assertThat(betaAnalyticsPluginActivity.installCount()).contains(0L)
        assertThat(betaAnalyticsPluginActivity.invocationCount()).isEqualTo(0L)
        assertThat(betaAnalyticsPluginActivity.pluginName()).isEqualTo("plugin_name")
        assertThat(betaAnalyticsPluginActivity.chatCoworkUnifiedMetrics())
            .contains(BetaAnalyticsPluginActivity.ChatCoworkUnifiedMetrics.of(0L))
        assertThat(betaAnalyticsPluginActivity.pluginId()).contains("plugin_id")
        assertThat(betaAnalyticsPluginActivity.product()).contains("product")
        assertThat(betaAnalyticsPluginActivity.rbacGroupId()).contains("rbac_group_id")
        assertThat(betaAnalyticsPluginActivity.rbacGroupName()).contains("rbac_group_name")
        assertThat(betaAnalyticsPluginActivity.userId()).contains("user_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsPluginActivity =
            BetaAnalyticsPluginActivity.builder()
                .claudeCodeMetrics(BetaAnalyticsPluginClaudeCodeMetrics.of(0L))
                .coworkMetrics(BetaAnalyticsPluginCoworkMetrics.of(0L))
                .distinctUserCount(0L)
                .installCount(0L)
                .invocationCount(0L)
                .pluginName("plugin_name")
                .chatCoworkUnifiedMetrics(
                    BetaAnalyticsPluginActivity.ChatCoworkUnifiedMetrics.of(0L)
                )
                .pluginId("plugin_id")
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        val roundtrippedBetaAnalyticsPluginActivity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsPluginActivity),
                jacksonTypeRef<BetaAnalyticsPluginActivity>(),
            )

        assertThat(roundtrippedBetaAnalyticsPluginActivity).isEqualTo(betaAnalyticsPluginActivity)
    }
}
