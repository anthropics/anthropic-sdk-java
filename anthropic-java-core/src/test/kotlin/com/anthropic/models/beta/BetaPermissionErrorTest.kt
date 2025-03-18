// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPermissionErrorTest {

    @Test
    fun create() {
        val betaPermissionError = BetaPermissionError.builder().message("message").build()

        assertThat(betaPermissionError.message()).isEqualTo("message")
    }
}
