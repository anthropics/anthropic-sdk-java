package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThreadNotFoundErrorDetailsTest {

    @Test
    fun create() {
        val threadNotFoundErrorDetails = ThreadNotFoundErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val threadNotFoundErrorDetails = ThreadNotFoundErrorDetails.builder().build()

        val roundtrippedThreadNotFoundErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(threadNotFoundErrorDetails),
                jacksonTypeRef<ThreadNotFoundErrorDetails>(),
            )

        assertThat(roundtrippedThreadNotFoundErrorDetails).isEqualTo(threadNotFoundErrorDetails)
    }
}
