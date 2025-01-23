// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ContentBlockSourceTest {

    @Test
    fun createContentBlockSource() {
        val contentBlockSource =
            ContentBlockSource.builder()
                .content("string")
                .type(ContentBlockSource.Type.CONTENT)
                .build()
        assertThat(contentBlockSource).isNotNull
        assertThat(contentBlockSource.content())
            .isEqualTo(ContentBlockSource.Content.ofString("string"))
        assertThat(contentBlockSource.type()).isEqualTo(ContentBlockSource.Type.CONTENT)
    }
}
