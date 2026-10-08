package com.anthropic.models.beta.organization.analytics.connectors

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsConnectorActivity
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsConnectorChatMetrics
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsConnectorClaudeCodeMetrics
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsConnectorCoworkMetrics
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsConnectorOfficeMetrics
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsConnectorOfficeProductMetrics
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ConnectorListPageResponseTest {

    @Test
    fun create() {
        val connectorListPageResponse =
            ConnectorListPageResponse.builder()
                .addData(
                    BetaAnalyticsConnectorActivity.builder()
                        .chatMetrics(BetaAnalyticsConnectorChatMetrics.of(0L))
                        .claudeCodeMetrics(BetaAnalyticsConnectorClaudeCodeMetrics.of(0L))
                        .connectorName("connector_name")
                        .coworkMetrics(BetaAnalyticsConnectorCoworkMetrics.of(0L))
                        .distinctUserCount(0L)
                        .officeMetrics(
                            BetaAnalyticsConnectorOfficeMetrics.builder()
                                .excel(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                                .outlook(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                                .powerpoint(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                                .word(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                                .build()
                        )
                        .chatCoworkUnifiedMetrics(
                            BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.builder()
                                .chat(
                                    BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.Chat.of(
                                        0L
                                    )
                                )
                                .sessions(
                                    BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.Sessions
                                        .of(0L)
                                )
                                .build()
                        )
                        .connectorDisplayName("connector_display_name")
                        .individualAuthDistinctUserCount(0L)
                        .managedAuthDistinctUserCount(0L)
                        .product("product")
                        .rbacGroupId("rbac_group_id")
                        .rbacGroupName("rbac_group_name")
                        .readCallCount(0L)
                        .unclassifiedCallCount(0L)
                        .userId("user_id")
                        .writeCallCount(0L)
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(connectorListPageResponse.data())
            .containsExactly(
                BetaAnalyticsConnectorActivity.builder()
                    .chatMetrics(BetaAnalyticsConnectorChatMetrics.of(0L))
                    .claudeCodeMetrics(BetaAnalyticsConnectorClaudeCodeMetrics.of(0L))
                    .connectorName("connector_name")
                    .coworkMetrics(BetaAnalyticsConnectorCoworkMetrics.of(0L))
                    .distinctUserCount(0L)
                    .officeMetrics(
                        BetaAnalyticsConnectorOfficeMetrics.builder()
                            .excel(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                            .outlook(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                            .powerpoint(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                            .word(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                            .build()
                    )
                    .chatCoworkUnifiedMetrics(
                        BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.builder()
                            .chat(
                                BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.Chat.of(0L)
                            )
                            .sessions(
                                BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.Sessions.of(
                                    0L
                                )
                            )
                            .build()
                    )
                    .connectorDisplayName("connector_display_name")
                    .individualAuthDistinctUserCount(0L)
                    .managedAuthDistinctUserCount(0L)
                    .product("product")
                    .rbacGroupId("rbac_group_id")
                    .rbacGroupName("rbac_group_name")
                    .readCallCount(0L)
                    .unclassifiedCallCount(0L)
                    .userId("user_id")
                    .writeCallCount(0L)
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
                    BetaAnalyticsConnectorActivity.builder()
                        .chatMetrics(BetaAnalyticsConnectorChatMetrics.of(0L))
                        .claudeCodeMetrics(BetaAnalyticsConnectorClaudeCodeMetrics.of(0L))
                        .connectorName("connector_name")
                        .coworkMetrics(BetaAnalyticsConnectorCoworkMetrics.of(0L))
                        .distinctUserCount(0L)
                        .officeMetrics(
                            BetaAnalyticsConnectorOfficeMetrics.builder()
                                .excel(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                                .outlook(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                                .powerpoint(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                                .word(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                                .build()
                        )
                        .chatCoworkUnifiedMetrics(
                            BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.builder()
                                .chat(
                                    BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.Chat.of(
                                        0L
                                    )
                                )
                                .sessions(
                                    BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.Sessions
                                        .of(0L)
                                )
                                .build()
                        )
                        .connectorDisplayName("connector_display_name")
                        .individualAuthDistinctUserCount(0L)
                        .managedAuthDistinctUserCount(0L)
                        .product("product")
                        .rbacGroupId("rbac_group_id")
                        .rbacGroupName("rbac_group_name")
                        .readCallCount(0L)
                        .unclassifiedCallCount(0L)
                        .userId("user_id")
                        .writeCallCount(0L)
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
