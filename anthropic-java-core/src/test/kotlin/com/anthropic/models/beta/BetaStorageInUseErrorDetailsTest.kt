package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaStorageInUseErrorDetailsTest {

    @Test
    fun create() {
        val betaStorageInUseErrorDetails = BetaStorageInUseErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaStorageInUseErrorDetails = BetaStorageInUseErrorDetails.builder().build()

        val roundtrippedBetaStorageInUseErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaStorageInUseErrorDetails),
                jacksonTypeRef<BetaStorageInUseErrorDetails>(),
            )

        assertThat(roundtrippedBetaStorageInUseErrorDetails).isEqualTo(betaStorageInUseErrorDetails)
    }
}
