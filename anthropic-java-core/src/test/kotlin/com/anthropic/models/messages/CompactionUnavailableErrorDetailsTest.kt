package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CompactionUnavailableErrorDetailsTest {

    @Test
    fun create() {
        val compactionUnavailableErrorDetails = CompactionUnavailableErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val compactionUnavailableErrorDetails = CompactionUnavailableErrorDetails.builder().build()

        val roundtrippedCompactionUnavailableErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(compactionUnavailableErrorDetails),
                jacksonTypeRef<CompactionUnavailableErrorDetails>(),
            )

        assertThat(roundtrippedCompactionUnavailableErrorDetails)
            .isEqualTo(compactionUnavailableErrorDetails)
    }
}
