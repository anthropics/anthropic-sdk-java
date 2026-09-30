package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsProjectActivityTest {

    @Test
    fun create() {
        val betaAnalyticsProjectActivity =
            BetaAnalyticsProjectActivity.builder()
                .distinctUserCount(0L)
                .messageCount(0L)
                .projectId("project_id")
                .projectName("project_name")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdBy(
                    BetaAnalyticsUser.builder().id("id").emailAddress("email_address").build()
                )
                .distinctConversationCount(0L)
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        assertThat(betaAnalyticsProjectActivity.distinctUserCount()).isEqualTo(0L)
        assertThat(betaAnalyticsProjectActivity.messageCount()).isEqualTo(0L)
        assertThat(betaAnalyticsProjectActivity.projectId()).isEqualTo("project_id")
        assertThat(betaAnalyticsProjectActivity.projectName()).isEqualTo("project_name")
        assertThat(betaAnalyticsProjectActivity.createdAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaAnalyticsProjectActivity.createdBy())
            .contains(BetaAnalyticsUser.builder().id("id").emailAddress("email_address").build())
        assertThat(betaAnalyticsProjectActivity.distinctConversationCount()).contains(0L)
        assertThat(betaAnalyticsProjectActivity.product()).contains("product")
        assertThat(betaAnalyticsProjectActivity.rbacGroupId()).contains("rbac_group_id")
        assertThat(betaAnalyticsProjectActivity.rbacGroupName()).contains("rbac_group_name")
        assertThat(betaAnalyticsProjectActivity.userId()).contains("user_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsProjectActivity =
            BetaAnalyticsProjectActivity.builder()
                .distinctUserCount(0L)
                .messageCount(0L)
                .projectId("project_id")
                .projectName("project_name")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdBy(
                    BetaAnalyticsUser.builder().id("id").emailAddress("email_address").build()
                )
                .distinctConversationCount(0L)
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        val roundtrippedBetaAnalyticsProjectActivity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsProjectActivity),
                jacksonTypeRef<BetaAnalyticsProjectActivity>(),
            )

        assertThat(roundtrippedBetaAnalyticsProjectActivity).isEqualTo(betaAnalyticsProjectActivity)
    }
}
