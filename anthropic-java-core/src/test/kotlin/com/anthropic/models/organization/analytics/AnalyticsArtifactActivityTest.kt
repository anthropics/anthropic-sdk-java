package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsArtifactActivityTest {

    @Test
    fun create() {
        val analyticsArtifactActivity =
            AnalyticsArtifactActivity.builder()
                .artifactType("artifact_type")
                .artifactsCreatedCount(0L)
                .distinctUserCount(0L)
                .isShared(true)
                .publishedArtifactsCreatedCount(0L)
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        assertThat(analyticsArtifactActivity.artifactType()).isEqualTo("artifact_type")
        assertThat(analyticsArtifactActivity.artifactsCreatedCount()).isEqualTo(0L)
        assertThat(analyticsArtifactActivity.distinctUserCount()).isEqualTo(0L)
        assertThat(analyticsArtifactActivity.isShared()).isEqualTo(true)
        assertThat(analyticsArtifactActivity.publishedArtifactsCreatedCount()).isEqualTo(0L)
        assertThat(analyticsArtifactActivity.product()).contains("product")
        assertThat(analyticsArtifactActivity.rbacGroupId()).contains("rbac_group_id")
        assertThat(analyticsArtifactActivity.rbacGroupName()).contains("rbac_group_name")
        assertThat(analyticsArtifactActivity.userId()).contains("user_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsArtifactActivity =
            AnalyticsArtifactActivity.builder()
                .artifactType("artifact_type")
                .artifactsCreatedCount(0L)
                .distinctUserCount(0L)
                .isShared(true)
                .publishedArtifactsCreatedCount(0L)
                .product("product")
                .rbacGroupId("rbac_group_id")
                .rbacGroupName("rbac_group_name")
                .userId("user_id")
                .build()

        val roundtrippedAnalyticsArtifactActivity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsArtifactActivity),
                jacksonTypeRef<AnalyticsArtifactActivity>(),
            )

        assertThat(roundtrippedAnalyticsArtifactActivity).isEqualTo(analyticsArtifactActivity)
    }
}
