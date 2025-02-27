// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UrlPdfSourceTest {

    @Test
    fun createUrlPdfSource() {
        val urlPdfSource = UrlPdfSource.builder().url("url").build()
        assertThat(urlPdfSource).isNotNull
        assertThat(urlPdfSource.url()).isEqualTo("url")
    }
}
