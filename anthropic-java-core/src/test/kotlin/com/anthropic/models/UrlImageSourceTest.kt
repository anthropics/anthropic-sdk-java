// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UrlImageSourceTest {

    @Test
    fun createUrlImageSource() {
        val urlImageSource = UrlImageSource.builder().url("url").build()
        assertThat(urlImageSource).isNotNull
        assertThat(urlImageSource.url()).isEqualTo("url")
    }
}
