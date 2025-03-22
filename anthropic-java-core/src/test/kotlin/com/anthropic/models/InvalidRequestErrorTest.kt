// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InvalidRequestErrorTest {

    @Test
    fun create() {
        val invalidRequestError = InvalidRequestError.builder().message("message").build()

        assertThat(invalidRequestError.message()).isEqualTo("message")
    }
}
