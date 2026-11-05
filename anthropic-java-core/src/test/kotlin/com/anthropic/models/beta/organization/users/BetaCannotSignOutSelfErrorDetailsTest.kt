package com.anthropic.models.beta.organization.users

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCannotSignOutSelfErrorDetailsTest {

    @Test
    fun create() {
        val betaCannotSignOutSelfErrorDetails = BetaCannotSignOutSelfErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCannotSignOutSelfErrorDetails = BetaCannotSignOutSelfErrorDetails.builder().build()

        val roundtrippedBetaCannotSignOutSelfErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCannotSignOutSelfErrorDetails),
                jacksonTypeRef<BetaCannotSignOutSelfErrorDetails>(),
            )

        assertThat(roundtrippedBetaCannotSignOutSelfErrorDetails)
            .isEqualTo(betaCannotSignOutSelfErrorDetails)
    }
}
