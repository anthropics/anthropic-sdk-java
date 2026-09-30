package com.anthropic.models.beta.organization.rbacroles.permissions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacAllConnectorsPermissionResourceTest {

    @Test
    fun create() {
        val betaRbacAllConnectorsPermissionResource =
            BetaRbacAllConnectorsPermissionResource.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacAllConnectorsPermissionResource =
            BetaRbacAllConnectorsPermissionResource.builder().build()

        val roundtrippedBetaRbacAllConnectorsPermissionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacAllConnectorsPermissionResource),
                jacksonTypeRef<BetaRbacAllConnectorsPermissionResource>(),
            )

        assertThat(roundtrippedBetaRbacAllConnectorsPermissionResource)
            .isEqualTo(betaRbacAllConnectorsPermissionResource)
    }
}
