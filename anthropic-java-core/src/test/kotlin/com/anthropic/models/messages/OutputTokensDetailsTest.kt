// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OutputTokensDetailsTest {

    @Test
    fun create() {
        val outputTokensDetails = OutputTokensDetails.of(0L)

        assertThat(outputTokensDetails.thinkingTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val outputTokensDetails = OutputTokensDetails.of(0L)

        val roundtrippedOutputTokensDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(outputTokensDetails),
                jacksonTypeRef<OutputTokensDetails>(),
            )

        assertThat(roundtrippedOutputTokensDetails).isEqualTo(outputTokensDetails)
    }
}
