package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThreadFingerprintMismatchErrorDetailsTest {

    @Test
    fun create() {
        val threadFingerprintMismatchErrorDetails =
            ThreadFingerprintMismatchErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val threadFingerprintMismatchErrorDetails =
            ThreadFingerprintMismatchErrorDetails.builder().build()

        val roundtrippedThreadFingerprintMismatchErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(threadFingerprintMismatchErrorDetails),
                jacksonTypeRef<ThreadFingerprintMismatchErrorDetails>(),
            )

        assertThat(roundtrippedThreadFingerprintMismatchErrorDetails)
            .isEqualTo(threadFingerprintMismatchErrorDetails)
    }
}
