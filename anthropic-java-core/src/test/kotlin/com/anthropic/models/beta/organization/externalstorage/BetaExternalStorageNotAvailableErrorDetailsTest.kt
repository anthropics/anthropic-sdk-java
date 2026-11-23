package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaExternalStorageNotAvailableErrorDetailsTest {

    @Test
    fun create() {
        val betaExternalStorageNotAvailableErrorDetails =
            BetaExternalStorageNotAvailableErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaExternalStorageNotAvailableErrorDetails =
            BetaExternalStorageNotAvailableErrorDetails.builder().build()

        val roundtrippedBetaExternalStorageNotAvailableErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaExternalStorageNotAvailableErrorDetails),
                jacksonTypeRef<BetaExternalStorageNotAvailableErrorDetails>(),
            )

        assertThat(roundtrippedBetaExternalStorageNotAvailableErrorDetails)
            .isEqualTo(betaExternalStorageNotAvailableErrorDetails)
    }
}
