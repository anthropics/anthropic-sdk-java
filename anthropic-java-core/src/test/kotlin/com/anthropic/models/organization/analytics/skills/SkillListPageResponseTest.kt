package com.anthropic.models.organization.analytics.skills

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.analytics.AnalyticsSkillActivity
import com.anthropic.models.organization.analytics.AnalyticsSkillChatCoworkUnifiedChatMetrics
import com.anthropic.models.organization.analytics.AnalyticsSkillChatCoworkUnifiedMetrics
import com.anthropic.models.organization.analytics.AnalyticsSkillChatCoworkUnifiedSessionsMetrics
import com.anthropic.models.organization.analytics.AnalyticsSkillChatMetrics
import com.anthropic.models.organization.analytics.AnalyticsSkillClaudeCodeMetrics
import com.anthropic.models.organization.analytics.AnalyticsSkillCoworkMetrics
import com.anthropic.models.organization.analytics.AnalyticsSkillOfficeMetrics
import com.anthropic.models.organization.analytics.AnalyticsSkillOfficeProductMetrics
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillListPageResponseTest {

    @Test
    fun create() {
        val skillListPageResponse =
            SkillListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(skillListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(skillListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val skillListPageResponse =
            SkillListPageResponse.builder()
                .addData(
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
