package com.anthropic.models.organization.rbacroles.permissions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacAllConnectorsPermissionResourceTest {

    @Test
    fun create() {
        val rbacAllConnectorsPermissionResource =
            RbacAllConnectorsPermissionResource.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacAllConnectorsPermissionResource =
            RbacAllConnectorsPermissionResource.builder().build()

        val roundtrippedRbacAllConnectorsPermissionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacAllConnectorsPermissionResource),
                jacksonTypeRef<RbacAllConnectorsPermissionResource>(),
            )

        assertThat(roundtrippedRbacAllConnectorsPermissionResource)
            .isEqualTo(rbacAllConnectorsPermissionResource)
    }
}
