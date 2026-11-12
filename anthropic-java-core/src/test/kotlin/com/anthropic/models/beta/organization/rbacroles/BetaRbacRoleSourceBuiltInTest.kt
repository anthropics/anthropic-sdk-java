package com.anthropic.models.beta.organization.rbacroles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacRoleSourceBuiltInTest {

    @Test
    fun create() {
        val betaRbacRoleSourceBuiltIn = BetaRbacRoleSourceBuiltIn.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacRoleSourceBuiltIn = BetaRbacRoleSourceBuiltIn.builder().build()

        val roundtrippedBetaRbacRoleSourceBuiltIn =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacRoleSourceBuiltIn),
                jacksonTypeRef<BetaRbacRoleSourceBuiltIn>(),
            )

        assertThat(roundtrippedBetaRbacRoleSourceBuiltIn).isEqualTo(betaRbacRoleSourceBuiltIn)
    }
}
