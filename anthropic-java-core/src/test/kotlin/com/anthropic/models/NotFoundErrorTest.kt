// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NotFoundErrorTest {

    @Test
    fun create() {
        val notFoundError = NotFoundError.builder().message("message").build()

        assertThat(notFoundError.message()).isEqualTo("message")
    }
}
