// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UrlPdfSourceTest {

    @Test
    fun create() {
        val urlPdfSource = UrlPdfSource.builder().url("url").build()

        assertThat(urlPdfSource.url()).isEqualTo("url")
    }
}
