// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextDeltaTest {

    @Test
    fun create() {
        val textDelta = TextDelta.builder().text("text").build()

        assertThat(textDelta.text()).isEqualTo("text")
    }
}
