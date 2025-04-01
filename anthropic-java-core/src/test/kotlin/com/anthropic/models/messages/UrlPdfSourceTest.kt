// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UrlPdfSourceTest {

    @Test
    fun create() {
        val urlPdfSource = UrlPdfSource.builder().url("url").build()

        assertThat(urlPdfSource.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val urlPdfSource = UrlPdfSource.builder().url("url").build()

        val roundtrippedUrlPdfSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(urlPdfSource),
                jacksonTypeRef<UrlPdfSource>(),
            )

        assertThat(roundtrippedUrlPdfSource).isEqualTo(urlPdfSource)
    }
}
