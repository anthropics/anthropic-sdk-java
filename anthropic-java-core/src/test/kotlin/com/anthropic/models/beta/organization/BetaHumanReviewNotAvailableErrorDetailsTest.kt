package com.anthropic.models.beta.organization

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaHumanReviewNotAvailableErrorDetailsTest {

    @Test
    fun create() {
        val betaHumanReviewNotAvailableErrorDetails =
            BetaHumanReviewNotAvailableErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaHumanReviewNotAvailableErrorDetails =
            BetaHumanReviewNotAvailableErrorDetails.builder().build()

        val roundtrippedBetaHumanReviewNotAvailableErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaHumanReviewNotAvailableErrorDetails),
                jacksonTypeRef<BetaHumanReviewNotAvailableErrorDetails>(),
            )

        assertThat(roundtrippedBetaHumanReviewNotAvailableErrorDetails)
            .isEqualTo(betaHumanReviewNotAvailableErrorDetails)
    }
}
