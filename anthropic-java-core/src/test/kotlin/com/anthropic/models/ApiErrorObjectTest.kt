// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ApiErrorObjectTest {

    @Test
    fun createApiErrorObject() {
        val apiErrorObject = ApiErrorObject.builder().message("message").build()
        assertThat(apiErrorObject).isNotNull
        assertThat(apiErrorObject.message()).isEqualTo("message")
    }
}
