package com.anthropic.models.beta.organization.analytics.skills

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSkillActivity
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSkillChatMetrics
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSkillClaudeCodeMetrics
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSkillCoworkMetrics
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSkillOfficeMetrics
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSkillOfficeProductMetrics
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillListPageResponseTest {

    @Test
    fun create() {
        val skillListPageResponse =
            SkillListPageResponse.builder()
                .addData(
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
                                .chat(
                                    BetaAnalyticsSkillActivity.ChatCoworkUnifiedMetrics.Chat.of(0L)
                                )
                                .sessions(
                                    BetaAnalyticsSkillActivity.ChatCoworkUnifiedMetrics.Sessions.of(
                                        0L
                                    )
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
                )
                .nextPage("next_page")
                .build()

        assertThat(skillListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(skillListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val skillListPageResponse =
            SkillListPageResponse.builder()
                .addData(
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
                                .chat(
                                    BetaAnalyticsSkillActivity.ChatCoworkUnifiedMetrics.Chat.of(0L)
                                )
                                .sessions(
                                    BetaAnalyticsSkillActivity.ChatCoworkUnifiedMetrics.Sessions.of(
                                        0L
                                    )
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedSkillListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(skillListPageResponse),
                jacksonTypeRef<SkillListPageResponse>(),
            )

        assertThat(roundtrippedSkillListPageResponse).isEqualTo(skillListPageResponse)
    }
}
