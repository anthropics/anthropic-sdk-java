package com.anthropic.models.beta.organization

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUnsupportedCharsetErrorDetailsTest {

    @Test
    fun create() {
        val betaUnsupportedCharsetErrorDetails =
            BetaUnsupportedCharsetErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUnsupportedCharsetErrorDetails =
            BetaUnsupportedCharsetErrorDetails.builder().build()

        val roundtrippedBetaUnsupportedCharsetErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUnsupportedCharsetErrorDetails),
                jacksonTypeRef<BetaUnsupportedCharsetErrorDetails>(),
            )

        assertThat(roundtrippedBetaUnsupportedCharsetErrorDetails)
            .isEqualTo(betaUnsupportedCharsetErrorDetails)
    }
}
