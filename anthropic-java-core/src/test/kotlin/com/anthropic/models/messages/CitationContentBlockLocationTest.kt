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
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endBlockIndex(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .startBlockIndex(0L)
                .build()

        assertThat(citationContentBlockLocation.citedText())
            .isEqualTo("The grass is green. The sky is blue.")
        assertThat(citationContentBlockLocation.documentIndex()).isEqualTo(0L)
        assertThat(citationContentBlockLocation.documentTitle()).contains("My Document")
        assertThat(citationContentBlockLocation.endBlockIndex()).isEqualTo(0L)
        assertThat(citationContentBlockLocation.fileId()).contains("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(citationContentBlockLocation.startBlockIndex()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationContentBlockLocation =
            CitationContentBlockLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endBlockIndex(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
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
