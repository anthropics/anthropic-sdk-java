package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTranscriptsStorageRequiredErrorDetailsTest {

    @Test
    fun create() {
        val betaTranscriptsStorageRequiredErrorDetails =
            BetaTranscriptsStorageRequiredErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTranscriptsStorageRequiredErrorDetails =
            BetaTranscriptsStorageRequiredErrorDetails.builder().build()

        val roundtrippedBetaTranscriptsStorageRequiredErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTranscriptsStorageRequiredErrorDetails),
                jacksonTypeRef<BetaTranscriptsStorageRequiredErrorDetails>(),
            )

        assertThat(roundtrippedBetaTranscriptsStorageRequiredErrorDetails)
            .isEqualTo(betaTranscriptsStorageRequiredErrorDetails)
    }
}
