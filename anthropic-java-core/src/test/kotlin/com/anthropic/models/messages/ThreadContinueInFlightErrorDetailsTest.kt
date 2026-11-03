package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThreadContinueInFlightErrorDetailsTest {

    @Test
    fun create() {
        val threadContinueInFlightErrorDetails =
            ThreadContinueInFlightErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val threadContinueInFlightErrorDetails =
            ThreadContinueInFlightErrorDetails.builder().build()

        val roundtrippedThreadContinueInFlightErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(threadContinueInFlightErrorDetails),
                jacksonTypeRef<ThreadContinueInFlightErrorDetails>(),
            )

        assertThat(roundtrippedThreadContinueInFlightErrorDetails)
            .isEqualTo(threadContinueInFlightErrorDetails)
    }
}
