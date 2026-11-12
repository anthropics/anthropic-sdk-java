package com.anthropic.models.organization.rbacroles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacRoleSourceBuiltInTest {

    @Test
    fun create() {
        val rbacRoleSourceBuiltIn = RbacRoleSourceBuiltIn.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacRoleSourceBuiltIn = RbacRoleSourceBuiltIn.builder().build()

        val roundtrippedRbacRoleSourceBuiltIn =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacRoleSourceBuiltIn),
                jacksonTypeRef<RbacRoleSourceBuiltIn>(),
            )

        assertThat(roundtrippedRbacRoleSourceBuiltIn).isEqualTo(rbacRoleSourceBuiltIn)
    }
}
