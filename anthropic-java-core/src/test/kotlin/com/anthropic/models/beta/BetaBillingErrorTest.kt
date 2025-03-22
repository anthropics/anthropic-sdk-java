// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBillingErrorTest {

    @Test
    fun create() {
        val betaBillingError = BetaBillingError.builder().message("message").build()

        assertThat(betaBillingError.message()).isEqualTo("message")
    }
}
