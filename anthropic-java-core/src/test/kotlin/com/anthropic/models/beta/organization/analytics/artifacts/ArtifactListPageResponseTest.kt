package com.anthropic.models.beta.organization.analytics.artifacts

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsArtifactActivity
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ArtifactListPageResponseTest {

    @Test
    fun create() {
        val artifactListPageResponse =
            ArtifactListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(artifactListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(artifactListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val artifactListPageResponse =
            ArtifactListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedArtifactListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(artifactListPageResponse),
                jacksonTypeRef<ArtifactListPageResponse>(),
            )

        assertThat(roundtrippedArtifactListPageResponse).isEqualTo(artifactListPageResponse)
    }
}
