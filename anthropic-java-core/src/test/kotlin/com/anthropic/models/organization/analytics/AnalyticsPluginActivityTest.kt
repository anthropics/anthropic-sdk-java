package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsPluginActivityTest {

    @Test
    fun create() {
        val analyticsPluginActivity =
            AnalyticsPluginActivity.builder()
                .claudeCodeMetrics(AnalyticsPluginClaudeCodeMetrics.of(0L))
                .coworkMetrics(AnalyticsPluginCoworkMetrics.of(0L))
                .distinctUserCount(0L)
                .installCount(0L)
                .invocationCount(0L)
                .pluginId("plugin_id")
                .pluginName("plugin_name")
                .chatCoworkUnifiedMetrics(AnalyticsPluginChatCoworkUnifiedMetrics.of(0L))
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        assertThat(analyticsPluginActivity.claudeCodeMetrics())
            .isEqualTo(AnalyticsPluginClaudeCodeMetrics.of(0L))
        assertThat(analyticsPluginActivity.coworkMetrics())
            .isEqualTo(AnalyticsPluginCoworkMetrics.of(0L))
        assertThat(analyticsPluginActivity.distinctUserCount()).isEqualTo(0L)
        assertThat(analyticsPluginActivity.installCount()).contains(0L)
        assertThat(analyticsPluginActivity.invocationCount()).isEqualTo(0L)
        assertThat(analyticsPluginActivity.pluginId()).contains("plugin_id")
        assertThat(analyticsPluginActivity.pluginName()).isEqualTo("plugin_name")
        assertThat(analyticsPluginActivity.chatCoworkUnifiedMetrics())
            .contains(AnalyticsPluginChatCoworkUnifiedMetrics.of(0L))
        assertThat(analyticsPluginActivity.product()).contains("product")
        assertThat(analyticsPluginActivity.rbacGroupId()).contains("rbac_group_id")
        assertThat(analyticsPluginActivity.rbacGroupName()).contains("rbac_group_name")
        assertThat(analyticsPluginActivity.userId()).contains("user_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsPluginActivity =
            AnalyticsPluginActivity.builder()
                .claudeCodeMetrics(AnalyticsPluginClaudeCodeMetrics.of(0L))
                .coworkMetrics(AnalyticsPluginCoworkMetrics.of(0L))
                .distinctUserCount(0L)
                .installCount(0L)
                .invocationCount(0L)
                .pluginId("plugin_id")
                .pluginName("plugin_name")
                .chatCoworkUnifiedMetrics(AnalyticsPluginChatCoworkUnifiedMetrics.of(0L))
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        val roundtrippedAnalyticsPluginActivity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsPluginActivity),
                jacksonTypeRef<AnalyticsPluginActivity>(),
            )

        assertThat(roundtrippedAnalyticsPluginActivity).isEqualTo(analyticsPluginActivity)
    }
}
