// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationContentBlockLocationTest {

    @Test
    fun create() {
        val citationContentBlockLocation =
            CitationContentBlockLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endBlockIndex(0L)
                .fileId("file_id")
                .startBlockIndex(0L)
                .build()

        assertThat(citationContentBlockLocation.citedText()).isEqualTo("cited_text")
        assertThat(citationContentBlockLocation.documentIndex()).isEqualTo(0L)
        assertThat(citationContentBlockLocation.documentTitle()).contains("document_title")
        assertThat(citationContentBlockLocation.endBlockIndex()).isEqualTo(0L)
        assertThat(citationContentBlockLocation.fileId()).contains("file_id")
        assertThat(citationContentBlockLocation.startBlockIndex()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationContentBlockLocation =
            CitationContentBlockLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endBlockIndex(0L)
                .fileId("file_id")
                .startBlockIndex(0L)
                .build()

        val roundtrippedCitationContentBlockLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationContentBlockLocation),
                jacksonTypeRef<CitationContentBlockLocation>(),
            )

        assertThat(roundtrippedCitationContentBlockLocation).isEqualTo(citationContentBlockLocation)
    }
}
