package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RequestBodyTooLargeAfterDecodingErrorDetailsTest {

    @Test
    fun create() {
        val requestBodyTooLargeAfterDecodingErrorDetails =
            RequestBodyTooLargeAfterDecodingErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val requestBodyTooLargeAfterDecodingErrorDetails =
            RequestBodyTooLargeAfterDecodingErrorDetails.builder().build()

        val roundtrippedRequestBodyTooLargeAfterDecodingErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(requestBodyTooLargeAfterDecodingErrorDetails),
                jacksonTypeRef<RequestBodyTooLargeAfterDecodingErrorDetails>(),
            )

        assertThat(roundtrippedRequestBodyTooLargeAfterDecodingErrorDetails)
            .isEqualTo(requestBodyTooLargeAfterDecodingErrorDetails)
    }
}
