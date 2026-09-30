package com.anthropic.models.beta.organization.rbacroles.permissions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacConnectorPermissionResourceTest {

    @Test
    fun create() {
        val betaRbacConnectorPermissionResource =
            BetaRbacConnectorPermissionResource.of("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")

        assertThat(betaRbacConnectorPermissionResource.connectorId())
            .isEqualTo("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacConnectorPermissionResource =
            BetaRbacConnectorPermissionResource.of("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")

        val roundtrippedBetaRbacConnectorPermissionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacConnectorPermissionResource),
                jacksonTypeRef<BetaRbacConnectorPermissionResource>(),
            )

        assertThat(roundtrippedBetaRbacConnectorPermissionResource)
            .isEqualTo(betaRbacConnectorPermissionResource)
    }
}
