package com.anthropic.models.organization.rbacroles.permissions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacRolePermissionTest {

    @Test
    fun create() {
        val rbacRolePermission =
            RbacRolePermission.builder()
                .action("use")
                .organizationResource("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")
                .build()

        assertThat(rbacRolePermission.action()).isEqualTo("use")
        assertThat(rbacRolePermission.resource())
            .isEqualTo(
                RbacRolePermission.Resource.ofOrganization("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacRolePermission =
            RbacRolePermission.builder()
                .action("use")
                .organizationResource("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")
                .build()

        val roundtrippedRbacRolePermission =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacRolePermission),
                jacksonTypeRef<RbacRolePermission>(),
            )

        assertThat(roundtrippedRbacRolePermission).isEqualTo(rbacRolePermission)
    }
}
