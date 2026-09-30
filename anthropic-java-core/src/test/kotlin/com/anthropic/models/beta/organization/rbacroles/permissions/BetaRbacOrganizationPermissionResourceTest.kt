package com.anthropic.models.beta.organization.rbacroles.permissions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacOrganizationPermissionResourceTest {

    @Test
    fun create() {
        val betaRbacOrganizationPermissionResource =
            BetaRbacOrganizationPermissionResource.of("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")

        assertThat(betaRbacOrganizationPermissionResource.organizationId())
            .isEqualTo("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacOrganizationPermissionResource =
            BetaRbacOrganizationPermissionResource.of("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")

        val roundtrippedBetaRbacOrganizationPermissionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacOrganizationPermissionResource),
                jacksonTypeRef<BetaRbacOrganizationPermissionResource>(),
            )

        assertThat(roundtrippedBetaRbacOrganizationPermissionResource)
            .isEqualTo(betaRbacOrganizationPermissionResource)
    }
}
