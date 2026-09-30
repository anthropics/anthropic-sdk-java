package com.anthropic.models.beta.organization.rbacroles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacRoleListPageResponseTest {

    @Test
    fun create() {
        val rbacRoleListPageResponse =
            RbacRoleListPageResponse.builder()
                .addData(
                    BetaRbacRole.builder()
                        .id("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .name("Project Editor")
                        .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .build()
                )
                .hasMore(true)
                .nextPage("eyJjdXJzb3IiOiAicmJhY19yb2xlXzAxIn0")
                .build()

        assertThat(rbacRoleListPageResponse.data())
            .containsExactly(
                BetaRbacRole.builder()
                    .id("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                    .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .name("Project Editor")
                    .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .build()
            )
        assertThat(rbacRoleListPageResponse.hasMore()).isEqualTo(true)
        assertThat(rbacRoleListPageResponse.nextPage())
            .contains("eyJjdXJzb3IiOiAicmJhY19yb2xlXzAxIn0")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacRoleListPageResponse =
            RbacRoleListPageResponse.builder()
                .addData(
                    BetaRbacRole.builder()
                        .id("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .name("Project Editor")
                        .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .build()
                )
                .hasMore(true)
                .nextPage("eyJjdXJzb3IiOiAicmJhY19yb2xlXzAxIn0")
                .build()

        val roundtrippedRbacRoleListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacRoleListPageResponse),
                jacksonTypeRef<RbacRoleListPageResponse>(),
            )

        assertThat(roundtrippedRbacRoleListPageResponse).isEqualTo(rbacRoleListPageResponse)
    }
}
