package com.anthropic.models.organization.rbacroles.permissions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacConnectorPermissionResourceTest {

    @Test
    fun create() {
        val rbacConnectorPermissionResource =
            RbacConnectorPermissionResource.of("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")

        assertThat(rbacConnectorPermissionResource.connectorId())
            .isEqualTo("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacConnectorPermissionResource =
            RbacConnectorPermissionResource.of("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")

        val roundtrippedRbacConnectorPermissionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacConnectorPermissionResource),
                jacksonTypeRef<RbacConnectorPermissionResource>(),
            )

        assertThat(roundtrippedRbacConnectorPermissionResource)
            .isEqualTo(rbacConnectorPermissionResource)
    }
}
