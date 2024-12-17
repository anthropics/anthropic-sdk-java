// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OverloadedErrorTest {

    @Test
    fun createOverloadedError() {
        val overloadedError =
            OverloadedError.builder()
                .message("message")
                .type(OverloadedError.Type.OVERLOADED_ERROR)
                .build()
        assertThat(overloadedError).isNotNull
        assertThat(overloadedError.message()).isEqualTo("message")
        assertThat(overloadedError.type()).isEqualTo(OverloadedError.Type.OVERLOADED_ERROR)
    }
}
