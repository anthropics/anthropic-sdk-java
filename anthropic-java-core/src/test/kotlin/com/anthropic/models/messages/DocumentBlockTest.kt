// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DocumentBlockTest {

    @Test
    fun create() {
        val documentBlock =
            DocumentBlock.builder()
                .citations(CitationsConfig.builder().enabled(true).build())
                .base64Source("U3RhaW5sZXNzIHJvY2tz")
                .title("title")
                .build()

        assertThat(documentBlock.citations())
            .contains(CitationsConfig.builder().enabled(true).build())
        assertThat(documentBlock.source())
            .isEqualTo(
                DocumentBlock.Source.ofBase64(
                    Base64PdfSource.builder().data("U3RhaW5sZXNzIHJvY2tz").build()
                )
            )
        assertThat(documentBlock.title()).contains("title")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val documentBlock =
            DocumentBlock.builder()
                .citations(CitationsConfig.builder().enabled(true).build())
                .base64Source("U3RhaW5sZXNzIHJvY2tz")
                .title("title")
                .build()

        val roundtrippedDocumentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(documentBlock),
                jacksonTypeRef<DocumentBlock>(),
            )

        assertThat(roundtrippedDocumentBlock).isEqualTo(documentBlock)
    }
}
