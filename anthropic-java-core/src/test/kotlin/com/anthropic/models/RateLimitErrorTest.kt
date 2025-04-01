// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RateLimitErrorTest {

    @Test
    fun create() {
        val rateLimitError = RateLimitError.builder().message("message").build()

        assertThat(rateLimitError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rateLimitError = RateLimitError.builder().message("message").build()

        val roundtrippedRateLimitError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rateLimitError),
                jacksonTypeRef<RateLimitError>(),
            )

        assertThat(roundtrippedRateLimitError).isEqualTo(rateLimitError)
    }
}
