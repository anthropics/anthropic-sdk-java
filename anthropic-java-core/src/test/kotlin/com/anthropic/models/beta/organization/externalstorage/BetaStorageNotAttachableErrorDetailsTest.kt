package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaStorageNotAttachableErrorDetailsTest {

    @Test
    fun create() {
        val betaStorageNotAttachableErrorDetails =
            BetaStorageNotAttachableErrorDetails.of("inference_data_privacy.safeguards_storage")

        assertThat(betaStorageNotAttachableErrorDetails.field())
            .isEqualTo("inference_data_privacy.safeguards_storage")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaStorageNotAttachableErrorDetails =
            BetaStorageNotAttachableErrorDetails.of("inference_data_privacy.safeguards_storage")

        val roundtrippedBetaStorageNotAttachableErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaStorageNotAttachableErrorDetails),
                jacksonTypeRef<BetaStorageNotAttachableErrorDetails>(),
            )

        assertThat(roundtrippedBetaStorageNotAttachableErrorDetails)
            .isEqualTo(betaStorageNotAttachableErrorDetails)
    }
}
