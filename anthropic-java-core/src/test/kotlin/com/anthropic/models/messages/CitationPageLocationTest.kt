// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationPageLocationTest {

    @Test
    fun create() {
        val citationPageLocation =
            CitationPageLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endPageNumber(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .startPageNumber(1L)
                .build()

        assertThat(citationPageLocation.citedText())
            .isEqualTo("The grass is green. The sky is blue.")
        assertThat(citationPageLocation.documentIndex()).isEqualTo(0L)
        assertThat(citationPageLocation.documentTitle()).contains("My Document")
        assertThat(citationPageLocation.endPageNumber()).isEqualTo(0L)
        assertThat(citationPageLocation.fileId()).contains("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(citationPageLocation.startPageNumber()).isEqualTo(1L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationPageLocation =
            CitationPageLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endPageNumber(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .startPageNumber(1L)
                .build()

        val roundtrippedCitationPageLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationPageLocation),
                jacksonTypeRef<CitationPageLocation>(),
            )

        assertThat(roundtrippedCitationPageLocation).isEqualTo(citationPageLocation)
    }
}
