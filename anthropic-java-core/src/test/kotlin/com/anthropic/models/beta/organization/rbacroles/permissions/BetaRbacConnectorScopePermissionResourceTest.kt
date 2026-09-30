package com.anthropic.models.beta.organization.rbacroles.permissions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacConnectorScopePermissionResourceTest {

    @Test
    fun create() {
        val betaRbacConnectorScopePermissionResource =
            BetaRbacConnectorScopePermissionResource.builder()
                .connectorId("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
                .scope("offline_access")
                .build()

        assertThat(betaRbacConnectorScopePermissionResource.connectorId())
            .isEqualTo("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
        assertThat(betaRbacConnectorScopePermissionResource.scope()).isEqualTo("offline_access")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacConnectorScopePermissionResource =
            BetaRbacConnectorScopePermissionResource.builder()
                .connectorId("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
                .scope("offline_access")
                .build()

        val roundtrippedBetaRbacConnectorScopePermissionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacConnectorScopePermissionResource),
                jacksonTypeRef<BetaRbacConnectorScopePermissionResource>(),
            )

        assertThat(roundtrippedBetaRbacConnectorScopePermissionResource)
            .isEqualTo(betaRbacConnectorScopePermissionResource)
    }
}
