package com.anthropic.models.beta.organization

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationNotEligibleErrorDetailsTest {

    @Test
    fun create() {
        val betaOrganizationNotEligibleErrorDetails =
            BetaOrganizationNotEligibleErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationNotEligibleErrorDetails =
            BetaOrganizationNotEligibleErrorDetails.builder().build()

        val roundtrippedBetaOrganizationNotEligibleErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationNotEligibleErrorDetails),
                jacksonTypeRef<BetaOrganizationNotEligibleErrorDetails>(),
            )

        assertThat(roundtrippedBetaOrganizationNotEligibleErrorDetails)
            .isEqualTo(betaOrganizationNotEligibleErrorDetails)
    }
}
