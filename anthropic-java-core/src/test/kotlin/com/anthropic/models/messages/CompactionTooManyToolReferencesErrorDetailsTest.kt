package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CompactionTooManyToolReferencesErrorDetailsTest {

    @Test
    fun create() {
        val compactionTooManyToolReferencesErrorDetails =
            CompactionTooManyToolReferencesErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val compactionTooManyToolReferencesErrorDetails =
            CompactionTooManyToolReferencesErrorDetails.builder().build()

        val roundtrippedCompactionTooManyToolReferencesErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(compactionTooManyToolReferencesErrorDetails),
                jacksonTypeRef<CompactionTooManyToolReferencesErrorDetails>(),
            )

        assertThat(roundtrippedCompactionTooManyToolReferencesErrorDetails)
            .isEqualTo(compactionTooManyToolReferencesErrorDetails)
    }
}
