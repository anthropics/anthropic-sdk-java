package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CompactionContentMismatchErrorDetailsTest {

    @Test
    fun create() {
        val compactionContentMismatchErrorDetails =
            CompactionContentMismatchErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val compactionContentMismatchErrorDetails =
            CompactionContentMismatchErrorDetails.builder().build()

        val roundtrippedCompactionContentMismatchErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(compactionContentMismatchErrorDetails),
                jacksonTypeRef<CompactionContentMismatchErrorDetails>(),
            )

        assertThat(roundtrippedCompactionContentMismatchErrorDetails)
            .isEqualTo(compactionContentMismatchErrorDetails)
    }
}
