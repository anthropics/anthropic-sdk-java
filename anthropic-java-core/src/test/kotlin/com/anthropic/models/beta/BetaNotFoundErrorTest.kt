// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaNotFoundErrorTest {

    @Test
    fun create() {
        val betaNotFoundError = BetaNotFoundError.builder().message("message").build()

        assertThat(betaNotFoundError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaNotFoundError = BetaNotFoundError.builder().message("message").build()

        val roundtrippedBetaNotFoundError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaNotFoundError),
                jacksonTypeRef<BetaNotFoundError>(),
            )

        assertThat(roundtrippedBetaNotFoundError).isEqualTo(betaNotFoundError)
    }
}
