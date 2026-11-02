package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsProjectActivityTest {

    @Test
    fun create() {
        val analyticsProjectActivity =
            AnalyticsProjectActivity.builder()
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdBy(AnalyticsUser.builder().id("id").emailAddress("email_address").build())
                .distinctConversationCount(0L)
                .distinctUserCount(0L)
                .messageCount(0L)
                .projectId("project_id")
                .projectName("project_name")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        assertThat(analyticsProjectActivity.createdAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(analyticsProjectActivity.createdBy())
            .contains(AnalyticsUser.builder().id("id").emailAddress("email_address").build())
        assertThat(analyticsProjectActivity.distinctConversationCount()).contains(0L)
        assertThat(analyticsProjectActivity.distinctUserCount()).isEqualTo(0L)
        assertThat(analyticsProjectActivity.messageCount()).isEqualTo(0L)
        assertThat(analyticsProjectActivity.projectId()).isEqualTo("project_id")
        assertThat(analyticsProjectActivity.projectName()).isEqualTo("project_name")
        assertThat(analyticsProjectActivity.rbacGroupId()).contains("rbac_group_id")
        assertThat(analyticsProjectActivity.rbacGroupName()).contains("rbac_group_name")
        assertThat(analyticsProjectActivity.userId()).contains("user_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsProjectActivity =
            AnalyticsProjectActivity.builder()
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdBy(AnalyticsUser.builder().id("id").emailAddress("email_address").build())
                .distinctConversationCount(0L)
                .distinctUserCount(0L)
                .messageCount(0L)
                .projectId("project_id")
                .projectName("project_name")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        val roundtrippedAnalyticsProjectActivity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsProjectActivity),
                jacksonTypeRef<AnalyticsProjectActivity>(),
            )

        assertThat(roundtrippedAnalyticsProjectActivity).isEqualTo(analyticsProjectActivity)
    }
}
