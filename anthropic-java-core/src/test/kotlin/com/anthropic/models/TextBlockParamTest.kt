// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TextBlockParamTest {

    @Test
    fun createTextBlockParam() {
        val textBlockParam =
            TextBlockParam.builder()
                .text("x")
                .type(TextBlockParam.Type.TEXT)
                .cacheControl(
                    CacheControlEphemeral.builder()
                        .type(CacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .build()
        assertThat(textBlockParam).isNotNull
        assertThat(textBlockParam.text()).isEqualTo("x")
        assertThat(textBlockParam.type()).isEqualTo(TextBlockParam.Type.TEXT)
        assertThat(textBlockParam.cacheControl())
            .contains(
                CacheControlEphemeral.builder().type(CacheControlEphemeral.Type.EPHEMERAL).build()
            )
    }
}
