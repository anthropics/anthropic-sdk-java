package com.anthropic.models.beta.organization.users

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCannotSignOutOwnerErrorDetailsTest {

    @Test
    fun create() {
        val betaCannotSignOutOwnerErrorDetails =
            BetaCannotSignOutOwnerErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCannotSignOutOwnerErrorDetails =
            BetaCannotSignOutOwnerErrorDetails.builder().build()

        val roundtrippedBetaCannotSignOutOwnerErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCannotSignOutOwnerErrorDetails),
                jacksonTypeRef<BetaCannotSignOutOwnerErrorDetails>(),
            )

        assertThat(roundtrippedBetaCannotSignOutOwnerErrorDetails)
            .isEqualTo(betaCannotSignOutOwnerErrorDetails)
    }
}
