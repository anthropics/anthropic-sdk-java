package com.anthropic.models.organization.rbacroles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacRoleSourceCustomTest {

    @Test
    fun create() {
        val rbacRoleSourceCustom = RbacRoleSourceCustom.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacRoleSourceCustom = RbacRoleSourceCustom.builder().build()

        val roundtrippedRbacRoleSourceCustom =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacRoleSourceCustom),
                jacksonTypeRef<RbacRoleSourceCustom>(),
            )

        assertThat(roundtrippedRbacRoleSourceCustom).isEqualTo(rbacRoleSourceCustom)
    }
}
