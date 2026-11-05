package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationNotEnrolledInBetaErrorDetailsTest {

    @Test
    fun create() {
        val betaOrganizationNotEnrolledInBetaErrorDetails =
            BetaOrganizationNotEnrolledInBetaErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationNotEnrolledInBetaErrorDetails =
            BetaOrganizationNotEnrolledInBetaErrorDetails.builder().build()

        val roundtrippedBetaOrganizationNotEnrolledInBetaErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationNotEnrolledInBetaErrorDetails),
                jacksonTypeRef<BetaOrganizationNotEnrolledInBetaErrorDetails>(),
            )

        assertThat(roundtrippedBetaOrganizationNotEnrolledInBetaErrorDetails)
            .isEqualTo(betaOrganizationNotEnrolledInBetaErrorDetails)
    }
}
