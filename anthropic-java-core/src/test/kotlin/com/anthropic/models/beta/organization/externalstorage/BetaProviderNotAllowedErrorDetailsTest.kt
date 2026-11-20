package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaProviderNotAllowedErrorDetailsTest {

    @Test
    fun create() {
        val betaProviderNotAllowedErrorDetails =
            BetaProviderNotAllowedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaProviderNotAllowedErrorDetails =
            BetaProviderNotAllowedErrorDetails.builder().build()

        val roundtrippedBetaProviderNotAllowedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaProviderNotAllowedErrorDetails),
                jacksonTypeRef<BetaProviderNotAllowedErrorDetails>(),
            )

        assertThat(roundtrippedBetaProviderNotAllowedErrorDetails)
            .isEqualTo(betaProviderNotAllowedErrorDetails)
    }
}
