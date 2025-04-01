// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOverloadedErrorTest {

    @Test
    fun create() {
        val betaOverloadedError = BetaOverloadedError.builder().message("message").build()

        assertThat(betaOverloadedError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOverloadedError = BetaOverloadedError.builder().message("message").build()

        val roundtrippedBetaOverloadedError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOverloadedError),
                jacksonTypeRef<BetaOverloadedError>(),
            )

        assertThat(roundtrippedBetaOverloadedError).isEqualTo(betaOverloadedError)
    }
}
