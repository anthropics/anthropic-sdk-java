package com.anthropic.models.organization.rbacroles.permissions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacConnectorScopePermissionResourceTest {

    @Test
    fun create() {
        val rbacConnectorScopePermissionResource =
            RbacConnectorScopePermissionResource.builder()
                .connectorId("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
                .scope("offline_access")
                .build()

        assertThat(rbacConnectorScopePermissionResource.connectorId())
            .isEqualTo("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
        assertThat(rbacConnectorScopePermissionResource.scope()).isEqualTo("offline_access")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacConnectorScopePermissionResource =
            RbacConnectorScopePermissionResource.builder()
                .connectorId("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
                .scope("offline_access")
                .build()

        val roundtrippedRbacConnectorScopePermissionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacConnectorScopePermissionResource),
                jacksonTypeRef<RbacConnectorScopePermissionResource>(),
            )

        assertThat(roundtrippedRbacConnectorScopePermissionResource)
            .isEqualTo(rbacConnectorScopePermissionResource)
    }
}
