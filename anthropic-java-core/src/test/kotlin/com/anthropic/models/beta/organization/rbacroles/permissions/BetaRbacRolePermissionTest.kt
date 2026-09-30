package com.anthropic.models.beta.organization.rbacroles.permissions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacRolePermissionTest {

    @Test
    fun create() {
        val betaRbacRolePermission =
            BetaRbacRolePermission.builder()
                .action("use")
                .organizationResource("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")
                .build()

        assertThat(betaRbacRolePermission.action()).isEqualTo("use")
        assertThat(betaRbacRolePermission.resource())
            .isEqualTo(
                BetaRbacRolePermission.Resource.ofOrganization(
                    "3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e"
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacRolePermission =
            BetaRbacRolePermission.builder()
                .action("use")
                .organizationResource("3c4f5e6d-7a8b-49c0-9d1e-2f3a4b5c6d7e")
                .build()

        val roundtrippedBetaRbacRolePermission =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacRolePermission),
                jacksonTypeRef<BetaRbacRolePermission>(),
            )

        assertThat(roundtrippedBetaRbacRolePermission).isEqualTo(betaRbacRolePermission)
    }
}
