package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGcpProjectControlledByAnotherOrganizationErrorDetailsTest {

    @Test
    fun create() {
        val betaGcpProjectControlledByAnotherOrganizationErrorDetails =
            BetaGcpProjectControlledByAnotherOrganizationErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGcpProjectControlledByAnotherOrganizationErrorDetails =
            BetaGcpProjectControlledByAnotherOrganizationErrorDetails.builder().build()

        val roundtrippedBetaGcpProjectControlledByAnotherOrganizationErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaGcpProjectControlledByAnotherOrganizationErrorDetails
                ),
                jacksonTypeRef<BetaGcpProjectControlledByAnotherOrganizationErrorDetails>(),
            )

        assertThat(roundtrippedBetaGcpProjectControlledByAnotherOrganizationErrorDetails)
            .isEqualTo(betaGcpProjectControlledByAnotherOrganizationErrorDetails)
    }
}
