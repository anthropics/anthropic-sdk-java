package com.anthropic.models.beta.organization.analytics.plugins

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsPluginActivity
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsPluginClaudeCodeMetrics
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsPluginCoworkMetrics
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PluginListPageResponseTest {

    @Test
    fun create() {
        val pluginListPageResponse =
            PluginListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(pluginListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(pluginListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val pluginListPageResponse =
            PluginListPageResponse.builder()
                .addData(
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
