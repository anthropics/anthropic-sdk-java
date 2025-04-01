// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BillingErrorTest {

    @Test
    fun create() {
        val billingError = BillingError.builder().message("message").build()

        assertThat(billingError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val billingError = BillingError.builder().message("message").build()

        val roundtrippedBillingError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(billingError),
                jacksonTypeRef<BillingError>(),
            )

        assertThat(roundtrippedBillingError).isEqualTo(billingError)
    }
}
