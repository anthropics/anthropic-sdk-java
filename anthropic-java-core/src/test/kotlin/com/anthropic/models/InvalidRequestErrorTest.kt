// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InvalidRequestErrorTest {

    @Test
    fun createInvalidRequestError() {
        val invalidRequestError = InvalidRequestError.builder().message("message").build()
        assertThat(invalidRequestError).isNotNull
        assertThat(invalidRequestError.message()).isEqualTo("message")
    }
}
