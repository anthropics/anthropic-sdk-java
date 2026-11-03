package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EnforcedSpendLimitReachedErrorDetailsTest {

    @Test
    fun create() {
        val enforcedSpendLimitReachedErrorDetails =
            EnforcedSpendLimitReachedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val enforcedSpendLimitReachedErrorDetails =
            EnforcedSpendLimitReachedErrorDetails.builder().build()

        val roundtrippedEnforcedSpendLimitReachedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(enforcedSpendLimitReachedErrorDetails),
                jacksonTypeRef<EnforcedSpendLimitReachedErrorDetails>(),
            )

        assertThat(roundtrippedEnforcedSpendLimitReachedErrorDetails)
            .isEqualTo(enforcedSpendLimitReachedErrorDetails)
    }
}
