// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NotFoundErrorTest {

    @Test
    fun createNotFoundError() {
        val notFoundError = NotFoundError.builder().message("message").build()
        assertThat(notFoundError).isNotNull
        assertThat(notFoundError.message()).isEqualTo("message")
    }
}
