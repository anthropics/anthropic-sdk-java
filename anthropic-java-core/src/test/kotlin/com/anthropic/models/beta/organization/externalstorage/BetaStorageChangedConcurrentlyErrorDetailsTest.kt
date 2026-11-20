package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaStorageChangedConcurrentlyErrorDetailsTest {

    @Test
    fun create() {
        val betaStorageChangedConcurrentlyErrorDetails =
            BetaStorageChangedConcurrentlyErrorDetails.of(
                "inference_data_privacy.safeguards_storage"
            )

        assertThat(betaStorageChangedConcurrentlyErrorDetails.field())
            .isEqualTo("inference_data_privacy.safeguards_storage")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaStorageChangedConcurrentlyErrorDetails =
            BetaStorageChangedConcurrentlyErrorDetails.of(
                "inference_data_privacy.safeguards_storage"
            )

        val roundtrippedBetaStorageChangedConcurrentlyErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaStorageChangedConcurrentlyErrorDetails),
                jacksonTypeRef<BetaStorageChangedConcurrentlyErrorDetails>(),
            )

        assertThat(roundtrippedBetaStorageChangedConcurrentlyErrorDetails)
            .isEqualTo(betaStorageChangedConcurrentlyErrorDetails)
    }
}
