package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RequestBodyEncodingInvalidErrorDetailsTest {

    @Test
    fun create() {
        val requestBodyEncodingInvalidErrorDetails =
            RequestBodyEncodingInvalidErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val requestBodyEncodingInvalidErrorDetails =
            RequestBodyEncodingInvalidErrorDetails.builder().build()

        val roundtrippedRequestBodyEncodingInvalidErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(requestBodyEncodingInvalidErrorDetails),
                jacksonTypeRef<RequestBodyEncodingInvalidErrorDetails>(),
            )

        assertThat(roundtrippedRequestBodyEncodingInvalidErrorDetails)
            .isEqualTo(requestBodyEncodingInvalidErrorDetails)
    }
}
