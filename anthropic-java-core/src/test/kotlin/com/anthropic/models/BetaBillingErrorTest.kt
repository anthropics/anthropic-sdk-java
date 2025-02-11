// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaBillingErrorTest {

    @Test
    fun createBetaBillingError() {
        val betaBillingError = BetaBillingError.builder().message("message").build()
        assertThat(betaBillingError).isNotNull
        assertThat(betaBillingError.message()).isEqualTo("message")
    }
}
