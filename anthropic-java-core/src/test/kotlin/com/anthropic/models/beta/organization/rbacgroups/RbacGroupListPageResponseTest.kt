package com.anthropic.models.beta.organization.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupListPageResponseTest {

    @Test
    fun create() {
        val rbacGroupListPageResponse =
            RbacGroupListPageResponse.builder()
                .addData(
                    BetaRbacGroup.builder()
                        .id("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .name("Engineering")
                        .addRoleId("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                        .sourceType(BetaRbacGroup.SourceType.DIRECT)
                        .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .build()
                )
                .hasMore(false)
                .nextPage("eyJjdXJzb3IiOiAicmJhY19ncm91cF8wMSJ9")
                .build()

        assertThat(rbacGroupListPageResponse.data())
            .containsExactly(
                BetaRbacGroup.builder()
                    .id("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                    .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .name("Engineering")
                    .addRoleId("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                    .sourceType(BetaRbacGroup.SourceType.DIRECT)
                    .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .build()
            )
        assertThat(rbacGroupListPageResponse.hasMore()).isEqualTo(false)
        assertThat(rbacGroupListPageResponse.nextPage())
            .contains("eyJjdXJzb3IiOiAicmJhY19ncm91cF8wMSJ9")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacGroupListPageResponse =
            RbacGroupListPageResponse.builder()
                .addData(
                    BetaRbacGroup.builder()
                        .id("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .name("Engineering")
                        .addRoleId("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                        .sourceType(BetaRbacGroup.SourceType.DIRECT)
                        .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .build()
                )
                .hasMore(false)
                .nextPage("eyJjdXJzb3IiOiAicmJhY19ncm91cF8wMSJ9")
                .build()

        val roundtrippedRbacGroupListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacGroupListPageResponse),
                jacksonTypeRef<RbacGroupListPageResponse>(),
            )

        assertThat(roundtrippedRbacGroupListPageResponse).isEqualTo(rbacGroupListPageResponse)
    }
}
