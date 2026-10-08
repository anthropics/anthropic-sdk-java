package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsConnectorActivityTest {

    @Test
    fun create() {
        val betaAnalyticsConnectorActivity =
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
                        .chat(BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.Chat.of(0L))
                        .sessions(
                            BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.Sessions.of(0L)
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

        assertThat(betaAnalyticsConnectorActivity.chatMetrics())
            .isEqualTo(BetaAnalyticsConnectorChatMetrics.of(0L))
        assertThat(betaAnalyticsConnectorActivity.claudeCodeMetrics())
            .isEqualTo(BetaAnalyticsConnectorClaudeCodeMetrics.of(0L))
        assertThat(betaAnalyticsConnectorActivity.connectorName()).isEqualTo("connector_name")
        assertThat(betaAnalyticsConnectorActivity.coworkMetrics())
            .isEqualTo(BetaAnalyticsConnectorCoworkMetrics.of(0L))
        assertThat(betaAnalyticsConnectorActivity.distinctUserCount()).isEqualTo(0L)
        assertThat(betaAnalyticsConnectorActivity.officeMetrics())
            .isEqualTo(
                BetaAnalyticsConnectorOfficeMetrics.builder()
                    .excel(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                    .outlook(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                    .powerpoint(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                    .word(BetaAnalyticsConnectorOfficeProductMetrics.of(0L))
                    .build()
            )
        assertThat(betaAnalyticsConnectorActivity.chatCoworkUnifiedMetrics())
            .contains(
                BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.builder()
                    .chat(BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.Chat.of(0L))
                    .sessions(
                        BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.Sessions.of(0L)
                    )
                    .build()
            )
        assertThat(betaAnalyticsConnectorActivity.connectorDisplayName())
            .contains("connector_display_name")
        assertThat(betaAnalyticsConnectorActivity.individualAuthDistinctUserCount()).contains(0L)
        assertThat(betaAnalyticsConnectorActivity.managedAuthDistinctUserCount()).contains(0L)
        assertThat(betaAnalyticsConnectorActivity.product()).contains("product")
        assertThat(betaAnalyticsConnectorActivity.rbacGroupId()).contains("rbac_group_id")
        assertThat(betaAnalyticsConnectorActivity.rbacGroupName()).contains("rbac_group_name")
        assertThat(betaAnalyticsConnectorActivity.readCallCount()).contains(0L)
        assertThat(betaAnalyticsConnectorActivity.unclassifiedCallCount()).contains(0L)
        assertThat(betaAnalyticsConnectorActivity.userId()).contains("user_id")
        assertThat(betaAnalyticsConnectorActivity.writeCallCount()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsConnectorActivity =
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
                        .chat(BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.Chat.of(0L))
                        .sessions(
                            BetaAnalyticsConnectorActivity.ChatCoworkUnifiedMetrics.Sessions.of(0L)
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

        val roundtrippedBetaAnalyticsConnectorActivity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsConnectorActivity),
                jacksonTypeRef<BetaAnalyticsConnectorActivity>(),
            )

        assertThat(roundtrippedBetaAnalyticsConnectorActivity)
            .isEqualTo(betaAnalyticsConnectorActivity)
    }
}
