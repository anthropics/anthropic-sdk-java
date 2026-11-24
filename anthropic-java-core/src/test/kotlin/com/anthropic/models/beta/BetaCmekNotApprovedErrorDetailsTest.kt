package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCmekNotApprovedErrorDetailsTest {

    @Test
    fun create() {
        val betaCmekNotApprovedErrorDetails = BetaCmekNotApprovedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCmekNotApprovedErrorDetails = BetaCmekNotApprovedErrorDetails.builder().build()

        val roundtrippedBetaCmekNotApprovedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCmekNotApprovedErrorDetails),
                jacksonTypeRef<BetaCmekNotApprovedErrorDetails>(),
            )

        assertThat(roundtrippedBetaCmekNotApprovedErrorDetails)
            .isEqualTo(betaCmekNotApprovedErrorDetails)
    }
}
