package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaStorageProfileStillRequiredErrorDetailsTest {

    @Test
    fun create() {
        val betaStorageProfileStillRequiredErrorDetails =
            BetaStorageProfileStillRequiredErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaStorageProfileStillRequiredErrorDetails =
            BetaStorageProfileStillRequiredErrorDetails.builder().build()

        val roundtrippedBetaStorageProfileStillRequiredErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaStorageProfileStillRequiredErrorDetails),
                jacksonTypeRef<BetaStorageProfileStillRequiredErrorDetails>(),
            )

        assertThat(roundtrippedBetaStorageProfileStillRequiredErrorDetails)
            .isEqualTo(betaStorageProfileStillRequiredErrorDetails)
    }
}
