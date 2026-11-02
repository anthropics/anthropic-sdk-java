package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsSkillActivityTest {

    @Test
    fun create() {
        val analyticsSkillActivity =
            AnalyticsSkillActivity.builder()
                .attributedListPrice("attributed_list_price")
                .chatMetrics(AnalyticsSkillChatMetrics.of(0L))
                .claudeCodeMetrics(AnalyticsSkillClaudeCodeMetrics.of(0L))
                .coworkMetrics(AnalyticsSkillCoworkMetrics.of(0L))
                .currency("currency")
                .distinctUserCount(0L)
                .enableCount(0L)
                .estimatedOverageSpend("estimated_overage_spend")
                .invocationCount(0L)
                .officeMetrics(
                    AnalyticsSkillOfficeMetrics.builder()
                        .excel(AnalyticsSkillOfficeProductMetrics.of(0L))
                        .outlook(AnalyticsSkillOfficeProductMetrics.of(0L))
                        .powerpoint(AnalyticsSkillOfficeProductMetrics.of(0L))
                        .word(AnalyticsSkillOfficeProductMetrics.of(0L))
                        .build()
                )
                .shareStatus(AnalyticsSkillActivity.ShareStatus.ORGANIZATION)
                .skillDisplayName("skill_display_name")
                .skillName("skill_name")
                .chatCoworkUnifiedMetrics(
                    AnalyticsSkillChatCoworkUnifiedMetrics.builder()
                        .chat(AnalyticsSkillChatCoworkUnifiedChatMetrics.of(0L))
                        .sessions(AnalyticsSkillChatCoworkUnifiedSessionsMetrics.of(0L))
                        .build()
                )
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        assertThat(analyticsSkillActivity.attributedListPrice()).contains("attributed_list_price")
        assertThat(analyticsSkillActivity.chatMetrics()).isEqualTo(AnalyticsSkillChatMetrics.of(0L))
        assertThat(analyticsSkillActivity.claudeCodeMetrics())
            .isEqualTo(AnalyticsSkillClaudeCodeMetrics.of(0L))
        assertThat(analyticsSkillActivity.coworkMetrics())
            .isEqualTo(AnalyticsSkillCoworkMetrics.of(0L))
        assertThat(analyticsSkillActivity.currency()).contains("currency")
        assertThat(analyticsSkillActivity.distinctUserCount()).isEqualTo(0L)
        assertThat(analyticsSkillActivity.enableCount()).contains(0L)
        assertThat(analyticsSkillActivity.estimatedOverageSpend())
            .contains("estimated_overage_spend")
        assertThat(analyticsSkillActivity.invocationCount()).contains(0L)
        assertThat(analyticsSkillActivity.officeMetrics())
            .isEqualTo(
                AnalyticsSkillOfficeMetrics.builder()
                    .excel(AnalyticsSkillOfficeProductMetrics.of(0L))
                    .outlook(AnalyticsSkillOfficeProductMetrics.of(0L))
                    .powerpoint(AnalyticsSkillOfficeProductMetrics.of(0L))
                    .word(AnalyticsSkillOfficeProductMetrics.of(0L))
                    .build()
            )
        assertThat(analyticsSkillActivity.shareStatus())
            .contains(AnalyticsSkillActivity.ShareStatus.ORGANIZATION)
        assertThat(analyticsSkillActivity.skillDisplayName()).contains("skill_display_name")
        assertThat(analyticsSkillActivity.skillName()).isEqualTo("skill_name")
        assertThat(analyticsSkillActivity.chatCoworkUnifiedMetrics())
            .contains(
                AnalyticsSkillChatCoworkUnifiedMetrics.builder()
                    .chat(AnalyticsSkillChatCoworkUnifiedChatMetrics.of(0L))
                    .sessions(AnalyticsSkillChatCoworkUnifiedSessionsMetrics.of(0L))
                    .build()
            )
        assertThat(analyticsSkillActivity.product()).contains("product")
        assertThat(analyticsSkillActivity.rbacGroupId()).contains("rbac_group_id")
        assertThat(analyticsSkillActivity.rbacGroupName()).contains("rbac_group_name")
        assertThat(analyticsSkillActivity.userId()).contains("user_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsSkillActivity =
            AnalyticsSkillActivity.builder()
                .attributedListPrice("attributed_list_price")
                .chatMetrics(AnalyticsSkillChatMetrics.of(0L))
                .claudeCodeMetrics(AnalyticsSkillClaudeCodeMetrics.of(0L))
                .coworkMetrics(AnalyticsSkillCoworkMetrics.of(0L))
                .currency("currency")
                .distinctUserCount(0L)
                .enableCount(0L)
                .estimatedOverageSpend("estimated_overage_spend")
                .invocationCount(0L)
                .officeMetrics(
                    AnalyticsSkillOfficeMetrics.builder()
                        .excel(AnalyticsSkillOfficeProductMetrics.of(0L))
                        .outlook(AnalyticsSkillOfficeProductMetrics.of(0L))
                        .powerpoint(AnalyticsSkillOfficeProductMetrics.of(0L))
                        .word(AnalyticsSkillOfficeProductMetrics.of(0L))
                        .build()
                )
                .shareStatus(AnalyticsSkillActivity.ShareStatus.ORGANIZATION)
                .skillDisplayName("skill_display_name")
                .skillName("skill_name")
                .chatCoworkUnifiedMetrics(
                    AnalyticsSkillChatCoworkUnifiedMetrics.builder()
                        .chat(AnalyticsSkillChatCoworkUnifiedChatMetrics.of(0L))
                        .sessions(AnalyticsSkillChatCoworkUnifiedSessionsMetrics.of(0L))
                        .build()
                )
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        val roundtrippedAnalyticsSkillActivity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsSkillActivity),
                jacksonTypeRef<AnalyticsSkillActivity>(),
            )

        assertThat(roundtrippedAnalyticsSkillActivity).isEqualTo(analyticsSkillActivity)
    }
}
