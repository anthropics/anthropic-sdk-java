// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaApiErrorTest {

    @Test
    fun createBetaApiError() {
        val betaApiError = BetaApiError.builder().message("message").build()
        assertThat(betaApiError).isNotNull
        assertThat(betaApiError.message()).isEqualTo("message")
    }
}
