// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaOverloadedErrorTest {

    @Test
    fun createBetaOverloadedError() {
        val betaOverloadedError =
            BetaOverloadedError.builder()
                .message("message")
                .type(BetaOverloadedError.Type.OVERLOADED_ERROR)
                .build()
        assertThat(betaOverloadedError).isNotNull
        assertThat(betaOverloadedError.message()).isEqualTo("message")
        assertThat(betaOverloadedError.type()).isEqualTo(BetaOverloadedError.Type.OVERLOADED_ERROR)
    }
}
