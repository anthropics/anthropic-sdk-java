package com.anthropic.models.organization.analytics.connectors

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.analytics.AnalyticsConnectorActivity
import com.anthropic.models.organization.analytics.AnalyticsConnectorChatCoworkUnifiedChatMetrics
import com.anthropic.models.organization.analytics.AnalyticsConnectorChatCoworkUnifiedMetrics
import com.anthropic.models.organization.analytics.AnalyticsConnectorChatCoworkUnifiedSessionsMetrics
import com.anthropic.models.organization.analytics.AnalyticsConnectorChatMetrics
import com.anthropic.models.organization.analytics.AnalyticsConnectorClaudeCodeMetrics
import com.anthropic.models.organization.analytics.AnalyticsConnectorCoworkMetrics
import com.anthropic.models.organization.analytics.AnalyticsConnectorOfficeMetrics
import com.anthropic.models.organization.analytics.AnalyticsConnectorOfficeProductMetrics
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ConnectorListPageResponseTest {

    @Test
    fun create() {
        val connectorListPageResponse =
            ConnectorListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(connectorListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(connectorListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val connectorListPageResponse =
            ConnectorListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedConnectorListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(connectorListPageResponse),
                jacksonTypeRef<ConnectorListPageResponse>(),
            )

        assertThat(roundtrippedConnectorListPageResponse).isEqualTo(connectorListPageResponse)
    }
}
