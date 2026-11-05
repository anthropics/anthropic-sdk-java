package com.anthropic.models.beta.organization.users

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCannotSignOutPrimaryOwnerErrorDetailsTest {

    @Test
    fun create() {
        val betaCannotSignOutPrimaryOwnerErrorDetails =
            BetaCannotSignOutPrimaryOwnerErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCannotSignOutPrimaryOwnerErrorDetails =
            BetaCannotSignOutPrimaryOwnerErrorDetails.builder().build()

        val roundtrippedBetaCannotSignOutPrimaryOwnerErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCannotSignOutPrimaryOwnerErrorDetails),
                jacksonTypeRef<BetaCannotSignOutPrimaryOwnerErrorDetails>(),
            )

        assertThat(roundtrippedBetaCannotSignOutPrimaryOwnerErrorDetails)
            .isEqualTo(betaCannotSignOutPrimaryOwnerErrorDetails)
    }
}
