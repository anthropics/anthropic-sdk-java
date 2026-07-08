// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationsDeltaTest {

    @Test
    fun create() {
        val citationsDelta =
            CitationsDelta.builder()
                .citation(
                    CitationCharLocation.builder()
                        .citedText("The grass is green. The sky is blue.")
                        .documentIndex(0L)
                        .documentTitle("My Document")
                        .endCharIndex(0L)
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        assertThat(citationsDelta.citation())
            .isEqualTo(
                CitationsDelta.Citation.ofCharLocation(
                    CitationCharLocation.builder()
                        .citedText("The grass is green. The sky is blue.")
                        .documentIndex(0L)
                        .documentTitle("My Document")
                        .endCharIndex(0L)
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .startCharIndex(0L)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationsDelta =
            CitationsDelta.builder()
                .citation(
                    CitationCharLocation.builder()
                        .citedText("The grass is green. The sky is blue.")
                        .documentIndex(0L)
                        .documentTitle("My Document")
                        .endCharIndex(0L)
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val roundtrippedCitationsDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationsDelta),
                jacksonTypeRef<CitationsDelta>(),
            )

        assertThat(roundtrippedCitationsDelta).isEqualTo(citationsDelta)
    }
}
