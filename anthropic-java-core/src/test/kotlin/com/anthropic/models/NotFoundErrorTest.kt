// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NotFoundErrorTest {

    @Test
    fun create() {
        val notFoundError = NotFoundError.builder().message("message").build()

        assertThat(notFoundError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val notFoundError = NotFoundError.builder().message("message").build()

        val roundtrippedNotFoundError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(notFoundError),
                jacksonTypeRef<NotFoundError>(),
            )

        assertThat(roundtrippedNotFoundError).isEqualTo(notFoundError)
    }
}
