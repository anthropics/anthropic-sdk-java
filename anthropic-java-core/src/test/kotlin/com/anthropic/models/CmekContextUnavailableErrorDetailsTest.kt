package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CmekContextUnavailableErrorDetailsTest {

    @Test
    fun create() {
        val cmekContextUnavailableErrorDetails =
            CmekContextUnavailableErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val cmekContextUnavailableErrorDetails =
            CmekContextUnavailableErrorDetails.builder().build()

        val roundtrippedCmekContextUnavailableErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cmekContextUnavailableErrorDetails),
                jacksonTypeRef<CmekContextUnavailableErrorDetails>(),
            )

        assertThat(roundtrippedCmekContextUnavailableErrorDetails)
            .isEqualTo(cmekContextUnavailableErrorDetails)
    }
}
