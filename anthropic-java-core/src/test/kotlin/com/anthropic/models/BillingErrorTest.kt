// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BillingErrorTest {

    @Test
    fun createBillingError() {
        val billingError =
            BillingError.builder().message("message").type(BillingError.Type.BILLING_ERROR).build()
        assertThat(billingError).isNotNull
        assertThat(billingError.message()).isEqualTo("message")
        assertThat(billingError.type()).isEqualTo(BillingError.Type.BILLING_ERROR)
    }
}
