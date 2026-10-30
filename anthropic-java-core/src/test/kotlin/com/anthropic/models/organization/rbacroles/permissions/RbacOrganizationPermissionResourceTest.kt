package com.anthropic.models.organization.rbacroles.permissions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacOrganizationPermissionResourceTest {

    @Test
    fun create() {
        val rbacOrganizationPermissionResource =
            RbacOrganizationPermissionResource.of("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")

        assertThat(rbacOrganizationPermissionResource.organizationId())
            .isEqualTo("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacOrganizationPermissionResource =
            RbacOrganizationPermissionResource.of("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")

        val roundtrippedRbacOrganizationPermissionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacOrganizationPermissionResource),
                jacksonTypeRef<RbacOrganizationPermissionResource>(),
            )

        assertThat(roundtrippedRbacOrganizationPermissionResource)
            .isEqualTo(rbacOrganizationPermissionResource)
    }
}
