// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DocumentBlockParamTest {

    @Test
    fun create() {
        val documentBlockParam =
            DocumentBlockParam.builder()
                .base64Source("U3RhaW5sZXNzIHJvY2tz")
                .cacheControl(CacheControlEphemeral.builder().build())
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .context("x")
                .title("x")
                .build()

        assertThat(documentBlockParam.source())
            .isEqualTo(
                DocumentBlockParam.Source.ofBase64(
                    Base64PdfSource.builder().data("U3RhaW5sZXNzIHJvY2tz").build()
                )
            )
        assertThat(documentBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().build())
        assertThat(documentBlockParam.citations())
            .contains(CitationsConfigParam.builder().enabled(true).build())
        assertThat(documentBlockParam.context()).contains("x")
        assertThat(documentBlockParam.title()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val documentBlockParam =
            DocumentBlockParam.builder()
                .base64Source("U3RhaW5sZXNzIHJvY2tz")
                .cacheControl(CacheControlEphemeral.builder().build())
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .context("x")
                .title("x")
                .build()

        val roundtrippedDocumentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(documentBlockParam),
                jacksonTypeRef<DocumentBlockParam>(),
            )

        assertThat(roundtrippedDocumentBlockParam).isEqualTo(documentBlockParam)
    }
}
