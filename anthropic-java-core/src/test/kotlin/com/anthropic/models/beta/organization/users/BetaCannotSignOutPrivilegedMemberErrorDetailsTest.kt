package com.anthropic.models.beta.organization.users

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCannotSignOutPrivilegedMemberErrorDetailsTest {

    @Test
    fun create() {
        val betaCannotSignOutPrivilegedMemberErrorDetails =
            BetaCannotSignOutPrivilegedMemberErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCannotSignOutPrivilegedMemberErrorDetails =
            BetaCannotSignOutPrivilegedMemberErrorDetails.builder().build()

        val roundtrippedBetaCannotSignOutPrivilegedMemberErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCannotSignOutPrivilegedMemberErrorDetails),
                jacksonTypeRef<BetaCannotSignOutPrivilegedMemberErrorDetails>(),
            )

        assertThat(roundtrippedBetaCannotSignOutPrivilegedMemberErrorDetails)
            .isEqualTo(betaCannotSignOutPrivilegedMemberErrorDetails)
    }
}
