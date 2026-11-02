package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsConnectorActivityTest {

    @Test
    fun create() {
        val analyticsConnectorActivity =
            AnalyticsConnectorActivity.builder()
                .chatMetrics(AnalyticsConnectorChatMetrics.of(0L))
                .claudeCodeMetrics(AnalyticsConnectorClaudeCodeMetrics.of(0L))
                .connectorDisplayName("connector_display_name")
                .connectorName("connector_name")
                .coworkMetrics(AnalyticsConnectorCoworkMetrics.of(0L))
                .distinctUserCount(0L)
                .individualAuthDistinctUserCount(0L)
                .managedAuthDistinctUserCount(0L)
                .officeMetrics(
                    AnalyticsConnectorOfficeMetrics.builder()
                        .excel(AnalyticsConnectorOfficeProductMetrics.of(0L))
                        .outlook(AnalyticsConnectorOfficeProductMetrics.of(0L))
                        .powerpoint(AnalyticsConnectorOfficeProductMetrics.of(0L))
                        .word(AnalyticsConnectorOfficeProductMetrics.of(0L))
                        .build()
                )
                .readCallCount(0L)
                .unclassifiedCallCount(0L)
                .writeCallCount(0L)
                .chatCoworkUnifiedMetrics(
                    AnalyticsConnectorChatCoworkUnifiedMetrics.builder()
                        .chat(AnalyticsConnectorChatCoworkUnifiedChatMetrics.of(0L))
                        .sessions(AnalyticsConnectorChatCoworkUnifiedSessionsMetrics.of(0L))
                        .build()
                )
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        assertThat(analyticsConnectorActivity.chatMetrics())
            .isEqualTo(AnalyticsConnectorChatMetrics.of(0L))
        assertThat(analyticsConnectorActivity.claudeCodeMetrics())
            .isEqualTo(AnalyticsConnectorClaudeCodeMetrics.of(0L))
        assertThat(analyticsConnectorActivity.connectorDisplayName())
            .contains("connector_display_name")
        assertThat(analyticsConnectorActivity.connectorName()).isEqualTo("connector_name")
        assertThat(analyticsConnectorActivity.coworkMetrics())
            .isEqualTo(AnalyticsConnectorCoworkMetrics.of(0L))
        assertThat(analyticsConnectorActivity.distinctUserCount()).isEqualTo(0L)
        assertThat(analyticsConnectorActivity.individualAuthDistinctUserCount()).contains(0L)
        assertThat(analyticsConnectorActivity.managedAuthDistinctUserCount()).contains(0L)
        assertThat(analyticsConnectorActivity.officeMetrics())
            .isEqualTo(
                AnalyticsConnectorOfficeMetrics.builder()
                    .excel(AnalyticsConnectorOfficeProductMetrics.of(0L))
                    .outlook(AnalyticsConnectorOfficeProductMetrics.of(0L))
                    .powerpoint(AnalyticsConnectorOfficeProductMetrics.of(0L))
                    .word(AnalyticsConnectorOfficeProductMetrics.of(0L))
                    .build()
            )
        assertThat(analyticsConnectorActivity.readCallCount()).contains(0L)
        assertThat(analyticsConnectorActivity.unclassifiedCallCount()).contains(0L)
        assertThat(analyticsConnectorActivity.writeCallCount()).contains(0L)
        assertThat(analyticsConnectorActivity.chatCoworkUnifiedMetrics())
            .contains(
                AnalyticsConnectorChatCoworkUnifiedMetrics.builder()
                    .chat(AnalyticsConnectorChatCoworkUnifiedChatMetrics.of(0L))
                    .sessions(AnalyticsConnectorChatCoworkUnifiedSessionsMetrics.of(0L))
                    .build()
            )
        assertThat(analyticsConnectorActivity.product()).contains("product")
        assertThat(analyticsConnectorActivity.rbacGroupId()).contains("rbac_group_id")
        assertThat(analyticsConnectorActivity.rbacGroupName()).contains("rbac_group_name")
        assertThat(analyticsConnectorActivity.userId()).contains("user_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsConnectorActivity =
            AnalyticsConnectorActivity.builder()
                .chatMetrics(AnalyticsConnectorChatMetrics.of(0L))
                .claudeCodeMetrics(AnalyticsConnectorClaudeCodeMetrics.of(0L))
                .connectorDisplayName("connector_display_name")
                .connectorName("connector_name")
                .coworkMetrics(AnalyticsConnectorCoworkMetrics.of(0L))
                .distinctUserCount(0L)
                .individualAuthDistinctUserCount(0L)
                .managedAuthDistinctUserCount(0L)
                .officeMetrics(
                    AnalyticsConnectorOfficeMetrics.builder()
                        .excel(AnalyticsConnectorOfficeProductMetrics.of(0L))
                        .outlook(AnalyticsConnectorOfficeProductMetrics.of(0L))
                        .powerpoint(AnalyticsConnectorOfficeProductMetrics.of(0L))
                        .word(AnalyticsConnectorOfficeProductMetrics.of(0L))
                        .build()
                )
                .readCallCount(0L)
                .unclassifiedCallCount(0L)
                .writeCallCount(0L)
                .chatCoworkUnifiedMetrics(
                    AnalyticsConnectorChatCoworkUnifiedMetrics.builder()
                        .chat(AnalyticsConnectorChatCoworkUnifiedChatMetrics.of(0L))
                        .sessions(AnalyticsConnectorChatCoworkUnifiedSessionsMetrics.of(0L))
                        .build()
                )
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        val roundtrippedAnalyticsConnectorActivity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsConnectorActivity),
                jacksonTypeRef<AnalyticsConnectorActivity>(),
            )

        assertThat(roundtrippedAnalyticsConnectorActivity).isEqualTo(analyticsConnectorActivity)
    }
}
