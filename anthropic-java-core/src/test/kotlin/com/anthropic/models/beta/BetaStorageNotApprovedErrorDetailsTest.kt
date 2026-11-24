package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaStorageNotApprovedErrorDetailsTest {

    @Test
    fun create() {
        val betaStorageNotApprovedErrorDetails =
            BetaStorageNotApprovedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaStorageNotApprovedErrorDetails =
            BetaStorageNotApprovedErrorDetails.builder().build()

        val roundtrippedBetaStorageNotApprovedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaStorageNotApprovedErrorDetails),
                jacksonTypeRef<BetaStorageNotApprovedErrorDetails>(),
            )

        assertThat(roundtrippedBetaStorageNotApprovedErrorDetails)
            .isEqualTo(betaStorageNotApprovedErrorDetails)
    }
}
