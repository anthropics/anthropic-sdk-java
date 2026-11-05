package com.anthropic.models.beta.organization.users

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCannotSignOutManagedMemberErrorDetailsTest {

    @Test
    fun create() {
        val betaCannotSignOutManagedMemberErrorDetails =
            BetaCannotSignOutManagedMemberErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCannotSignOutManagedMemberErrorDetails =
            BetaCannotSignOutManagedMemberErrorDetails.builder().build()

        val roundtrippedBetaCannotSignOutManagedMemberErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCannotSignOutManagedMemberErrorDetails),
                jacksonTypeRef<BetaCannotSignOutManagedMemberErrorDetails>(),
            )

        assertThat(roundtrippedBetaCannotSignOutManagedMemberErrorDetails)
            .isEqualTo(betaCannotSignOutManagedMemberErrorDetails)
    }
}
