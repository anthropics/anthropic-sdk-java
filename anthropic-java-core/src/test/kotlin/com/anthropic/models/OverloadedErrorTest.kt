// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OverloadedErrorTest {

    @Test
    fun create() {
        val overloadedError = OverloadedError.builder().message("message").build()

        assertThat(overloadedError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val overloadedError = OverloadedError.builder().message("message").build()

        val roundtrippedOverloadedError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(overloadedError),
                jacksonTypeRef<OverloadedError>(),
            )

        assertThat(roundtrippedOverloadedError).isEqualTo(overloadedError)
    }
}
