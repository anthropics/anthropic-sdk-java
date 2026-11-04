package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RequestBodyEncodingUnsupportedErrorDetailsTest {

    @Test
    fun create() {
        val requestBodyEncodingUnsupportedErrorDetails =
            RequestBodyEncodingUnsupportedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val requestBodyEncodingUnsupportedErrorDetails =
            RequestBodyEncodingUnsupportedErrorDetails.builder().build()

        val roundtrippedRequestBodyEncodingUnsupportedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(requestBodyEncodingUnsupportedErrorDetails),
                jacksonTypeRef<RequestBodyEncodingUnsupportedErrorDetails>(),
            )

        assertThat(roundtrippedRequestBodyEncodingUnsupportedErrorDetails)
            .isEqualTo(requestBodyEncodingUnsupportedErrorDetails)
    }
}
