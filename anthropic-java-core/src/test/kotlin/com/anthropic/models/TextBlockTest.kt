// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TextBlockTest {

    @Test
    fun createTextBlock() {
        val textBlock = TextBlock.builder().text("text").type(TextBlock.Type.TEXT).build()
        assertThat(textBlock).isNotNull
        assertThat(textBlock.text()).isEqualTo("text")
        assertThat(textBlock.type()).isEqualTo(TextBlock.Type.TEXT)
    }
}
