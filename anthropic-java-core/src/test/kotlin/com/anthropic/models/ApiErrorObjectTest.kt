// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ApiErrorObjectTest {

    @Test
    fun create() {
        val apiErrorObject = ApiErrorObject.builder().message("message").build()

        assertThat(apiErrorObject.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val apiErrorObject = ApiErrorObject.builder().message("message").build()

        val roundtrippedApiErrorObject =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(apiErrorObject),
                jacksonTypeRef<ApiErrorObject>(),
            )

        assertThat(roundtrippedApiErrorObject).isEqualTo(apiErrorObject)
    }
}
