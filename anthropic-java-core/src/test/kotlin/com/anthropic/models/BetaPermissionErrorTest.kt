// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaPermissionErrorTest {

    @Test
    fun createBetaPermissionError() {
        val betaPermissionError = BetaPermissionError.builder().message("message").build()
        assertThat(betaPermissionError).isNotNull
        assertThat(betaPermissionError.message()).isEqualTo("message")
    }
}
