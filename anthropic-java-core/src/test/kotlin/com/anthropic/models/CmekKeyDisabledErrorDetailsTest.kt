package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CmekKeyDisabledErrorDetailsTest {

    @Test
    fun create() {
        val cmekKeyDisabledErrorDetails = CmekKeyDisabledErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val cmekKeyDisabledErrorDetails = CmekKeyDisabledErrorDetails.builder().build()

        val roundtrippedCmekKeyDisabledErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cmekKeyDisabledErrorDetails),
                jacksonTypeRef<CmekKeyDisabledErrorDetails>(),
            )

        assertThat(roundtrippedCmekKeyDisabledErrorDetails).isEqualTo(cmekKeyDisabledErrorDetails)
    }
}
