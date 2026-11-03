package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CompactionToolChangesMismatchErrorDetailsTest {

    @Test
    fun create() {
        val compactionToolChangesMismatchErrorDetails =
            CompactionToolChangesMismatchErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val compactionToolChangesMismatchErrorDetails =
            CompactionToolChangesMismatchErrorDetails.builder().build()

        val roundtrippedCompactionToolChangesMismatchErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(compactionToolChangesMismatchErrorDetails),
                jacksonTypeRef<CompactionToolChangesMismatchErrorDetails>(),
            )

        assertThat(roundtrippedCompactionToolChangesMismatchErrorDetails)
            .isEqualTo(compactionToolChangesMismatchErrorDetails)
    }
}
