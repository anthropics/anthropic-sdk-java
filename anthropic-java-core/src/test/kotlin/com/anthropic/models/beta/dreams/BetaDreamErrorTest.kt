// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDreamErrorTest {

    @Test
    fun create() {
        val betaDreamError = BetaDreamError.builder().message("message").type("type").build()

        assertThat(betaDreamError.message()).isEqualTo("message")
        assertThat(betaDreamError.type()).isEqualTo("type")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamError = BetaDreamError.builder().message("message").type("type").build()

        val roundtrippedBetaDreamError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamError),
                jacksonTypeRef<BetaDreamError>(),
            )

        assertThat(roundtrippedBetaDreamError).isEqualTo(betaDreamError)
    }
}
