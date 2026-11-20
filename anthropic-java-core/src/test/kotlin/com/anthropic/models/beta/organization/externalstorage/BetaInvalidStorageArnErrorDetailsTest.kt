package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInvalidStorageArnErrorDetailsTest {

    @Test
    fun create() {
        val betaInvalidStorageArnErrorDetails = BetaInvalidStorageArnErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaInvalidStorageArnErrorDetails = BetaInvalidStorageArnErrorDetails.builder().build()

        val roundtrippedBetaInvalidStorageArnErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInvalidStorageArnErrorDetails),
                jacksonTypeRef<BetaInvalidStorageArnErrorDetails>(),
            )

        assertThat(roundtrippedBetaInvalidStorageArnErrorDetails)
            .isEqualTo(betaInvalidStorageArnErrorDetails)
    }
}
