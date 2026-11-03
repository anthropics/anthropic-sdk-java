package com.anthropic.models.beta.organization

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBillingFrozenErrorDetailsTest {

    @Test
    fun create() {
        val betaBillingFrozenErrorDetails = BetaBillingFrozenErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBillingFrozenErrorDetails = BetaBillingFrozenErrorDetails.builder().build()

        val roundtrippedBetaBillingFrozenErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBillingFrozenErrorDetails),
                jacksonTypeRef<BetaBillingFrozenErrorDetails>(),
            )

        assertThat(roundtrippedBetaBillingFrozenErrorDetails)
            .isEqualTo(betaBillingFrozenErrorDetails)
    }
}
