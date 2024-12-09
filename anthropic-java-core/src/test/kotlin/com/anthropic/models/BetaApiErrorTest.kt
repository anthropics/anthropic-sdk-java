// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaApiErrorTest {

    @Test
    fun createBetaApiError() {
        val betaApiError =
            BetaApiError.builder().message("message").type(BetaApiError.Type.API_ERROR).build()
        assertThat(betaApiError).isNotNull
        assertThat(betaApiError.message()).isEqualTo("message")
        assertThat(betaApiError.type()).isEqualTo(BetaApiError.Type.API_ERROR)
    }
}
