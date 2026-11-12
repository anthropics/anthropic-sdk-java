package com.anthropic.models.beta.organization.rbacroles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacRoleSourceCustomTest {

    @Test
    fun create() {
        val betaRbacRoleSourceCustom = BetaRbacRoleSourceCustom.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacRoleSourceCustom = BetaRbacRoleSourceCustom.builder().build()

        val roundtrippedBetaRbacRoleSourceCustom =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacRoleSourceCustom),
                jacksonTypeRef<BetaRbacRoleSourceCustom>(),
            )

        assertThat(roundtrippedBetaRbacRoleSourceCustom).isEqualTo(betaRbacRoleSourceCustom)
    }
}
