package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RampLimitExceededErrorDetailsTest {

    @Test
    fun create() {
        val rampLimitExceededErrorDetails = RampLimitExceededErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rampLimitExceededErrorDetails = RampLimitExceededErrorDetails.builder().build()

        val roundtrippedRampLimitExceededErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rampLimitExceededErrorDetails),
                jacksonTypeRef<RampLimitExceededErrorDetails>(),
            )

        assertThat(roundtrippedRampLimitExceededErrorDetails)
            .isEqualTo(rampLimitExceededErrorDetails)
    }
}
