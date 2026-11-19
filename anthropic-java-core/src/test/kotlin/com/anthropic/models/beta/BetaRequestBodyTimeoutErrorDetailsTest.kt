package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRequestBodyTimeoutErrorDetailsTest {

    @Test
    fun create() {
        val betaRequestBodyTimeoutErrorDetails =
            BetaRequestBodyTimeoutErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRequestBodyTimeoutErrorDetails =
            BetaRequestBodyTimeoutErrorDetails.builder().build()

        val roundtrippedBetaRequestBodyTimeoutErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRequestBodyTimeoutErrorDetails),
                jacksonTypeRef<BetaRequestBodyTimeoutErrorDetails>(),
            )

        assertThat(roundtrippedBetaRequestBodyTimeoutErrorDetails)
            .isEqualTo(betaRequestBodyTimeoutErrorDetails)
    }
}
