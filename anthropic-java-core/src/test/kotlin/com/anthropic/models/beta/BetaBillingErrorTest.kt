// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBillingErrorTest {

    @Test
    fun create() {
        val betaBillingError = BetaBillingError.builder().message("message").build()

        assertThat(betaBillingError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBillingError = BetaBillingError.builder().message("message").build()

        val roundtrippedBetaBillingError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBillingError),
                jacksonTypeRef<BetaBillingError>(),
            )

        assertThat(roundtrippedBetaBillingError).isEqualTo(betaBillingError)
    }
}
