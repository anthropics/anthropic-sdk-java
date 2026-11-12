package com.anthropic.models.organization

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacRoleNotFoundErrorDetailsTest {

    @Test
    fun create() {
        val rbacRoleNotFoundErrorDetails = RbacRoleNotFoundErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacRoleNotFoundErrorDetails = RbacRoleNotFoundErrorDetails.builder().build()

        val roundtrippedRbacRoleNotFoundErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacRoleNotFoundErrorDetails),
                jacksonTypeRef<RbacRoleNotFoundErrorDetails>(),
            )

        assertThat(roundtrippedRbacRoleNotFoundErrorDetails).isEqualTo(rbacRoleNotFoundErrorDetails)
    }
}
