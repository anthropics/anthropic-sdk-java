package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CompactionSignatureInvalidErrorDetailsTest {

    @Test
    fun create() {
        val compactionSignatureInvalidErrorDetails =
            CompactionSignatureInvalidErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val compactionSignatureInvalidErrorDetails =
            CompactionSignatureInvalidErrorDetails.builder().build()

        val roundtrippedCompactionSignatureInvalidErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(compactionSignatureInvalidErrorDetails),
                jacksonTypeRef<CompactionSignatureInvalidErrorDetails>(),
            )

        assertThat(roundtrippedCompactionSignatureInvalidErrorDetails)
            .isEqualTo(compactionSignatureInvalidErrorDetails)
    }
}
