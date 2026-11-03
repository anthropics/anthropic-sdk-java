package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CompactionNothingToSummarizeErrorDetailsTest {

    @Test
    fun create() {
        val compactionNothingToSummarizeErrorDetails =
            CompactionNothingToSummarizeErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val compactionNothingToSummarizeErrorDetails =
            CompactionNothingToSummarizeErrorDetails.builder().build()

        val roundtrippedCompactionNothingToSummarizeErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(compactionNothingToSummarizeErrorDetails),
                jacksonTypeRef<CompactionNothingToSummarizeErrorDetails>(),
            )

        assertThat(roundtrippedCompactionNothingToSummarizeErrorDetails)
            .isEqualTo(compactionNothingToSummarizeErrorDetails)
    }
}
