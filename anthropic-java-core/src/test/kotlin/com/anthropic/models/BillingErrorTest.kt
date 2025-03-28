// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BillingErrorTest {

    @Test
    fun create() {
        val billingError = BillingError.builder().message("message").build()

        assertThat(billingError.message()).isEqualTo("message")
    }
}
