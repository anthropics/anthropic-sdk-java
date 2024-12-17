// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ApiErrorObjectTest {

    @Test
    fun createApiErrorObject() {
        val apiErrorObject =
            ApiErrorObject.builder().message("message").type(ApiErrorObject.Type.API_ERROR).build()
        assertThat(apiErrorObject).isNotNull
        assertThat(apiErrorObject.message()).isEqualTo("message")
        assertThat(apiErrorObject.type()).isEqualTo(ApiErrorObject.Type.API_ERROR)
    }
}
