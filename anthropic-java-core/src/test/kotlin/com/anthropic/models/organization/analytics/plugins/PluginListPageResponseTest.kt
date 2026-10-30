package com.anthropic.models.organization.analytics.plugins

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.analytics.AnalyticsPluginActivity
import com.anthropic.models.organization.analytics.AnalyticsPluginChatCoworkUnifiedMetrics
import com.anthropic.models.organization.analytics.AnalyticsPluginClaudeCodeMetrics
import com.anthropic.models.organization.analytics.AnalyticsPluginCoworkMetrics
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PluginListPageResponseTest {

    @Test
    fun create() {
        val pluginListPageResponse =
            PluginListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(pluginListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(pluginListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val pluginListPageResponse =
            PluginListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedPluginListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(pluginListPageResponse),
                jacksonTypeRef<PluginListPageResponse>(),
            )

        assertThat(roundtrippedPluginListPageResponse).isEqualTo(pluginListPageResponse)
    }
}
