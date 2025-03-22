// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContentBlockSourceTest {

    @Test
    fun create() {
        val contentBlockSource = ContentBlockSource.builder().content("string").build()

        assertThat(contentBlockSource.content())
            .isEqualTo(ContentBlockSource.Content.ofString("string"))
    }
}
