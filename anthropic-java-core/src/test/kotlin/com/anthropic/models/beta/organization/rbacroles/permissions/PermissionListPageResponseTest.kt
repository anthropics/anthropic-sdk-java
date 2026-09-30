package com.anthropic.models.beta.organization.rbacroles.permissions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PermissionListPageResponseTest {

    @Test
    fun create() {
        val permissionListPageResponse =
            PermissionListPageResponse.builder()
                .addData(
                    BetaRbacRolePermission.builder()
                        .action("use")
                        .organizationResource("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")
                        .build()
                )
                .hasMore(true)
                .nextPage("eyJjdXJzb3IiOiAicmJhY19yb2xlXzAxIn0")
                .build()

        assertThat(permissionListPageResponse.data())
            .containsExactly(
                BetaRbacRolePermission.builder()
                    .action("use")
                    .organizationResource("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")
                    .build()
            )
        assertThat(permissionListPageResponse.hasMore()).isEqualTo(true)
        assertThat(permissionListPageResponse.nextPage())
            .contains("eyJjdXJzb3IiOiAicmJhY19yb2xlXzAxIn0")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val permissionListPageResponse =
            PermissionListPageResponse.builder()
                .addData(
                    BetaRbacRolePermission.builder()
                        .action("use")
                        .organizationResource("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")
                        .build()
                )
                .hasMore(true)
                .nextPage("eyJjdXJzb3IiOiAicmJhY19yb2xlXzAxIn0")
                .build()

        val roundtrippedPermissionListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(permissionListPageResponse),
                jacksonTypeRef<PermissionListPageResponse>(),
            )

        assertThat(roundtrippedPermissionListPageResponse).isEqualTo(permissionListPageResponse)
    }
}
