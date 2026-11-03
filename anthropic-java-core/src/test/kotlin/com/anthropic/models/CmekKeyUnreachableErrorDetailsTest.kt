package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CmekKeyUnreachableErrorDetailsTest {

    @Test
    fun create() {
        val cmekKeyUnreachableErrorDetails = CmekKeyUnreachableErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val cmekKeyUnreachableErrorDetails = CmekKeyUnreachableErrorDetails.builder().build()

        val roundtrippedCmekKeyUnreachableErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cmekKeyUnreachableErrorDetails),
                jacksonTypeRef<CmekKeyUnreachableErrorDetails>(),
            )

        assertThat(roundtrippedCmekKeyUnreachableErrorDetails)
            .isEqualTo(cmekKeyUnreachableErrorDetails)
    }
}
