package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaHumanReviewOnWithExternalStorageErrorDetailsTest {

    @Test
    fun create() {
        val betaHumanReviewOnWithExternalStorageErrorDetails =
            BetaHumanReviewOnWithExternalStorageErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaHumanReviewOnWithExternalStorageErrorDetails =
            BetaHumanReviewOnWithExternalStorageErrorDetails.builder().build()

        val roundtrippedBetaHumanReviewOnWithExternalStorageErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaHumanReviewOnWithExternalStorageErrorDetails),
                jacksonTypeRef<BetaHumanReviewOnWithExternalStorageErrorDetails>(),
            )

        assertThat(roundtrippedBetaHumanReviewOnWithExternalStorageErrorDetails)
            .isEqualTo(betaHumanReviewOnWithExternalStorageErrorDetails)
    }
}
