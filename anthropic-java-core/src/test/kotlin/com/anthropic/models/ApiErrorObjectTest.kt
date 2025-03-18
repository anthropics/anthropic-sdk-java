// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ApiErrorObjectTest {

    @Test
    fun create() {
        val apiErrorObject = ApiErrorObject.builder().message("message").build()

        assertThat(apiErrorObject.message()).isEqualTo("message")
    }
}
