package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaExternalStorageInUseErrorDetailsTest {

    @Test
    fun create() {
        val betaExternalStorageInUseErrorDetails =
            BetaExternalStorageInUseErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaExternalStorageInUseErrorDetails =
            BetaExternalStorageInUseErrorDetails.builder().build()

        val roundtrippedBetaExternalStorageInUseErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaExternalStorageInUseErrorDetails),
                jacksonTypeRef<BetaExternalStorageInUseErrorDetails>(),
            )

        assertThat(roundtrippedBetaExternalStorageInUseErrorDetails)
            .isEqualTo(betaExternalStorageInUseErrorDetails)
    }
}
