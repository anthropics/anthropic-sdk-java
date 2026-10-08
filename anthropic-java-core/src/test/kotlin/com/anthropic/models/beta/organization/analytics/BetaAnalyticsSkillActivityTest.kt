package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsSkillActivityTest {

    @Test
    fun create() {
        val betaAnalyticsSkillActivity =
            BetaAnalyticsSkillActivity.builder()
                .chatMetrics(BetaAnalyticsSkillChatMetrics.of(0L))
                .claudeCodeMetrics(BetaAnalyticsSkillClaudeCodeMetrics.of(0L))
                .coworkMetrics(BetaAnalyticsSkillCoworkMetrics.of(0L))
                .distinctUserCount(0L)
                .officeMetrics(
                    BetaAnalyticsSkillOfficeMetrics.builder()
                        .excel(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                        .outlook(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                        .powerpoint(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                        .word(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                        .build()
                )
                .skillName("skill_name")
                .attributedListPrice("attributed_list_price")
                .chatCoworkUnifiedMetrics(
                    BetaAnalyticsSkillActivity.ChatCoworkUnifiedMetrics.builder()
                        .chat(BetaAnalyticsSkillActivity.ChatCoworkUnifiedMetrics.Chat.of(0L))
                        .sessions(
                            BetaAnalyticsSkillActivity.ChatCoworkUnifiedMetrics.Sessions.of(0L)
                        )
                        .build()
                )
                .currency("currency")
                .enableCount(0L)
                .estimatedOverageSpend("estimated_overage_spend")
                .invocationCount(0L)
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .shareStatus(BetaAnalyticsSkillActivity.ShareStatus.ORGANIZATION)
                .skillDisplayName("skill_display_name")
                .userId("user_id")
                .build()

        assertThat(betaAnalyticsSkillActivity.chatMetrics())
            .isEqualTo(BetaAnalyticsSkillChatMetrics.of(0L))
        assertThat(betaAnalyticsSkillActivity.claudeCodeMetrics())
            .isEqualTo(BetaAnalyticsSkillClaudeCodeMetrics.of(0L))
        assertThat(betaAnalyticsSkillActivity.coworkMetrics())
            .isEqualTo(BetaAnalyticsSkillCoworkMetrics.of(0L))
        assertThat(betaAnalyticsSkillActivity.distinctUserCount()).isEqualTo(0L)
        assertThat(betaAnalyticsSkillActivity.officeMetrics())
            .isEqualTo(
                BetaAnalyticsSkillOfficeMetrics.builder()
                    .excel(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                    .outlook(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                    .powerpoint(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                    .word(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                    .build()
            )
        assertThat(betaAnalyticsSkillActivity.skillName()).isEqualTo("skill_name")
        assertThat(betaAnalyticsSkillActivity.attributedListPrice())
            .contains("attributed_list_price")
        assertThat(betaAnalyticsSkillActivity.chatCoworkUnifiedMetrics())
            .contains(
                BetaAnalyticsSkillActivity.ChatCoworkUnifiedMetrics.builder()
                    .chat(BetaAnalyticsSkillActivity.ChatCoworkUnifiedMetrics.Chat.of(0L))
                    .sessions(BetaAnalyticsSkillActivity.ChatCoworkUnifiedMetrics.Sessions.of(0L))
                    .build()
            )
        assertThat(betaAnalyticsSkillActivity.currency()).contains("currency")
        assertThat(betaAnalyticsSkillActivity.enableCount()).contains(0L)
        assertThat(betaAnalyticsSkillActivity.estimatedOverageSpend())
            .contains("estimated_overage_spend")
        assertThat(betaAnalyticsSkillActivity.invocationCount()).contains(0L)
        assertThat(betaAnalyticsSkillActivity.product()).contains("product")
        assertThat(betaAnalyticsSkillActivity.rbacGroupId()).contains("rbac_group_id")
        assertThat(betaAnalyticsSkillActivity.rbacGroupName()).contains("rbac_group_name")
        assertThat(betaAnalyticsSkillActivity.shareStatus())
            .contains(BetaAnalyticsSkillActivity.ShareStatus.ORGANIZATION)
        assertThat(betaAnalyticsSkillActivity.skillDisplayName()).contains("skill_display_name")
        assertThat(betaAnalyticsSkillActivity.userId()).contains("user_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsSkillActivity =
            BetaAnalyticsSkillActivity.builder()
                .chatMetrics(BetaAnalyticsSkillChatMetrics.of(0L))
                .claudeCodeMetrics(BetaAnalyticsSkillClaudeCodeMetrics.of(0L))
                .coworkMetrics(BetaAnalyticsSkillCoworkMetrics.of(0L))
                .distinctUserCount(0L)
                .officeMetrics(
                    BetaAnalyticsSkillOfficeMetrics.builder()
                        .excel(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                        .outlook(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                        .powerpoint(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                        .word(BetaAnalyticsSkillOfficeProductMetrics.of(0L))
                        .build()
                )
                .skillName("skill_name")
                .attributedListPrice("attributed_list_price")
                .chatCoworkUnifiedMetrics(
                    BetaAnalyticsSkillActivity.ChatCoworkUnifiedMetrics.builder()
                        .chat(BetaAnalyticsSkillActivity.ChatCoworkUnifiedMetrics.Chat.of(0L))
                        .sessions(
                            BetaAnalyticsSkillActivity.ChatCoworkUnifiedMetrics.Sessions.of(0L)
                        )
                        .build()
                )
                .currency("currency")
                .enableCount(0L)
                .estimatedOverageSpend("estimated_overage_spend")
                .invocationCount(0L)
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .shareStatus(BetaAnalyticsSkillActivity.ShareStatus.ORGANIZATION)
                .skillDisplayName("skill_display_name")
                .userId("user_id")
                .build()

        val roundtrippedBetaAnalyticsSkillActivity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsSkillActivity),
                jacksonTypeRef<BetaAnalyticsSkillActivity>(),
            )

        assertThat(roundtrippedBetaAnalyticsSkillActivity).isEqualTo(betaAnalyticsSkillActivity)
    }
}
