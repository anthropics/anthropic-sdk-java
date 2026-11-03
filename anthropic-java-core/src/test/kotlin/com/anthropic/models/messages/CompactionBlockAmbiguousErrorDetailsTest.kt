package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CompactionBlockAmbiguousErrorDetailsTest {

    @Test
    fun create() {
        val compactionBlockAmbiguousErrorDetails =
            CompactionBlockAmbiguousErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val compactionBlockAmbiguousErrorDetails =
            CompactionBlockAmbiguousErrorDetails.builder().build()

        val roundtrippedCompactionBlockAmbiguousErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(compactionBlockAmbiguousErrorDetails),
                jacksonTypeRef<CompactionBlockAmbiguousErrorDetails>(),
            )

        assertThat(roundtrippedCompactionBlockAmbiguousErrorDetails)
            .isEqualTo(compactionBlockAmbiguousErrorDetails)
    }
}
