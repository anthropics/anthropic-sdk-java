package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsArtifactActivityTest {

    @Test
    fun create() {
        val betaAnalyticsArtifactActivity =
            BetaAnalyticsArtifactActivity.builder()
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

        assertThat(betaAnalyticsArtifactActivity.artifactType()).isEqualTo("artifact_type")
        assertThat(betaAnalyticsArtifactActivity.artifactsCreatedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsArtifactActivity.distinctUserCount()).isEqualTo(0L)
        assertThat(betaAnalyticsArtifactActivity.isShared()).isEqualTo(true)
        assertThat(betaAnalyticsArtifactActivity.publishedArtifactsCreatedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsArtifactActivity.product()).contains("product")
        assertThat(betaAnalyticsArtifactActivity.rbacGroupId()).contains("rbac_group_id")
        assertThat(betaAnalyticsArtifactActivity.rbacGroupName()).contains("rbac_group_name")
        assertThat(betaAnalyticsArtifactActivity.userId()).contains("user_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsArtifactActivity =
            BetaAnalyticsArtifactActivity.builder()
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

        val roundtrippedBetaAnalyticsArtifactActivity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsArtifactActivity),
                jacksonTypeRef<BetaAnalyticsArtifactActivity>(),
            )

        assertThat(roundtrippedBetaAnalyticsArtifactActivity)
            .isEqualTo(betaAnalyticsArtifactActivity)
    }
}
