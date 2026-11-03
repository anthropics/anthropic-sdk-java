package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThreadAlreadyContinuedErrorDetailsTest {

    @Test
    fun create() {
        val threadAlreadyContinuedErrorDetails =
            ThreadAlreadyContinuedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val threadAlreadyContinuedErrorDetails =
            ThreadAlreadyContinuedErrorDetails.builder().build()

        val roundtrippedThreadAlreadyContinuedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(threadAlreadyContinuedErrorDetails),
                jacksonTypeRef<ThreadAlreadyContinuedErrorDetails>(),
            )

        assertThat(roundtrippedThreadAlreadyContinuedErrorDetails)
            .isEqualTo(threadAlreadyContinuedErrorDetails)
    }
}
