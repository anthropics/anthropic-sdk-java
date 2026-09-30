package com.anthropic.models.beta.organization.rbacroles.permissions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacConnectorToolPermissionResourceTest {

    @Test
    fun create() {
        val betaRbacConnectorToolPermissionResource =
            BetaRbacConnectorToolPermissionResource.builder()
                .connectorId("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
                .toolName("search_issues")
                .build()

        assertThat(betaRbacConnectorToolPermissionResource.connectorId())
            .isEqualTo("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
        assertThat(betaRbacConnectorToolPermissionResource.toolName()).isEqualTo("search_issues")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacConnectorToolPermissionResource =
            BetaRbacConnectorToolPermissionResource.builder()
                .connectorId("mcpsrv_01BqrKSXkKPpCJWuLoof7q8m")
                .toolName("search_issues")
                .build()

        val roundtrippedBetaRbacConnectorToolPermissionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacConnectorToolPermissionResource),
                jacksonTypeRef<BetaRbacConnectorToolPermissionResource>(),
            )

        assertThat(roundtrippedBetaRbacConnectorToolPermissionResource)
            .isEqualTo(betaRbacConnectorToolPermissionResource)
    }
}
