// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UrlImageSourceTest {

    @Test
    fun create() {
        val urlImageSource = UrlImageSource.builder().url("url").build()

        assertThat(urlImageSource.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val urlImageSource = UrlImageSource.builder().url("url").build()

        val roundtrippedUrlImageSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(urlImageSource),
                jacksonTypeRef<UrlImageSource>(),
            )

        assertThat(roundtrippedUrlImageSource).isEqualTo(urlImageSource)
    }
}
