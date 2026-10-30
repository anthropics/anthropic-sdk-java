package com.anthropic.models.organization.rbacroles.permissions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacConnectorToolPermissionResourceTest {

    @Test
    fun create() {
        val rbacConnectorToolPermissionResource =
            RbacConnectorToolPermissionResource.builder()
                .connectorId("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
                .toolName("search_issues")
                .build()

        assertThat(rbacConnectorToolPermissionResource.connectorId())
            .isEqualTo("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
        assertThat(rbacConnectorToolPermissionResource.toolName()).isEqualTo("search_issues")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacConnectorToolPermissionResource =
            RbacConnectorToolPermissionResource.builder()
                .connectorId("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
                .toolName("search_issues")
                .build()

        val roundtrippedRbacConnectorToolPermissionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacConnectorToolPermissionResource),
                jacksonTypeRef<RbacConnectorToolPermissionResource>(),
            )

        assertThat(roundtrippedRbacConnectorToolPermissionResource)
            .isEqualTo(rbacConnectorToolPermissionResource)
    }
}
