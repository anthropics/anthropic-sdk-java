// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationCharLocationTest {

    @Test
    fun create() {
        val citationCharLocation =
            CitationCharLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endCharIndex(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .startCharIndex(0L)
                .build()

        assertThat(citationCharLocation.citedText())
            .isEqualTo("The grass is green. The sky is blue.")
        assertThat(citationCharLocation.documentIndex()).isEqualTo(0L)
        assertThat(citationCharLocation.documentTitle()).contains("My Document")
        assertThat(citationCharLocation.endCharIndex()).isEqualTo(0L)
        assertThat(citationCharLocation.fileId()).contains("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(citationCharLocation.startCharIndex()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationCharLocation =
            CitationCharLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endCharIndex(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .startCharIndex(0L)
                .build()

        val roundtrippedCitationCharLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationCharLocation),
                jacksonTypeRef<CitationCharLocation>(),
            )

        assertThat(roundtrippedCitationCharLocation).isEqualTo(citationCharLocation)
    }
}
