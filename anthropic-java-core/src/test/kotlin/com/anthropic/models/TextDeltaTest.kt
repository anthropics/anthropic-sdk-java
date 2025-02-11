// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TextDeltaTest {

    @Test
    fun createTextDelta() {
        val textDelta = TextDelta.builder().text("text").build()
        assertThat(textDelta).isNotNull
        assertThat(textDelta.text()).isEqualTo("text")
    }
}
