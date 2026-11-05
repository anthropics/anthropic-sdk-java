package com.anthropic.models.beta.organization.users

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCannotSignOutMembershipAdminErrorDetailsTest {

    @Test
    fun create() {
        val betaCannotSignOutMembershipAdminErrorDetails =
            BetaCannotSignOutMembershipAdminErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCannotSignOutMembershipAdminErrorDetails =
            BetaCannotSignOutMembershipAdminErrorDetails.builder().build()

        val roundtrippedBetaCannotSignOutMembershipAdminErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCannotSignOutMembershipAdminErrorDetails),
                jacksonTypeRef<BetaCannotSignOutMembershipAdminErrorDetails>(),
            )

        assertThat(roundtrippedBetaCannotSignOutMembershipAdminErrorDetails)
            .isEqualTo(betaCannotSignOutMembershipAdminErrorDetails)
    }
}
