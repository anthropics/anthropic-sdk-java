// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaBillingErrorTest {

    @Test
    fun createBetaBillingError() {
        val betaBillingError =
            BetaBillingError.builder()
                .message("message")
                .type(BetaBillingError.Type.BILLING_ERROR)
                .build()
        assertThat(betaBillingError).isNotNull
        assertThat(betaBillingError.message()).isEqualTo("message")
        assertThat(betaBillingError.type()).isEqualTo(BetaBillingError.Type.BILLING_ERROR)
    }
}
