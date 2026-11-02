package com.anthropic.models.organization.analytics.apps.chat.projects

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.analytics.AnalyticsProjectActivity
import com.anthropic.models.organization.analytics.AnalyticsUser
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ProjectListPageResponseTest {

    @Test
    fun create() {
        val projectListPageResponse =
            ProjectListPageResponse.builder()
                .addData(
                    AnalyticsProjectActivity.builder()
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .createdBy(
                            AnalyticsUser.builder().id("id").emailAddress("email_address").build()
                        )
                        .distinctConversationCount(0L)
                        .distinctUserCount(0L)
                        .messageCount(0L)
                        .projectId("project_id")
                        .projectName("project_name")
                        .rbacGroupId("rbac_group_id")
                        .rbacGroupName("rbac_group_name")
                        .userId("user_id")
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(projectListPageResponse.data())
            .containsExactly(
                AnalyticsProjectActivity.builder()
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .createdBy(
                        AnalyticsUser.builder().id("id").emailAddress("email_address").build()
                    )
                    .distinctConversationCount(0L)
                    .distinctUserCount(0L)
                    .messageCount(0L)
                    .projectId("project_id")
                    .projectName("project_name")
                    .rbacGroupId("rbac_group_id")
                    .rbacGroupName("rbac_group_name")
                    .userId("user_id")
                    .build()
            )
        assertThat(projectListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val projectListPageResponse =
            ProjectListPageResponse.builder()
                .addData(
                    AnalyticsProjectActivity.builder()
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .createdBy(
                            AnalyticsUser.builder().id("id").emailAddress("email_address").build()
                        )
                        .distinctConversationCount(0L)
                        .distinctUserCount(0L)
                        .messageCount(0L)
                        .projectId("project_id")
                        .projectName("project_name")
                        .rbacGroupId("rbac_group_id")
                        .rbacGroupName("rbac_group_name")
                        .userId("user_id")
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedProjectListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(projectListPageResponse),
                jacksonTypeRef<ProjectListPageResponse>(),
            )

        assertThat(roundtrippedProjectListPageResponse).isEqualTo(projectListPageResponse)
    }
}
