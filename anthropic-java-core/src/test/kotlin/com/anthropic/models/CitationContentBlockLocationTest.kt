// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CitationContentBlockLocationTest {

    @Test
    fun createCitationContentBlockLocation() {
        val citationContentBlockLocation =
            CitationContentBlockLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()
        assertThat(citationContentBlockLocation).isNotNull
        assertThat(citationContentBlockLocation.citedText()).isEqualTo("cited_text")
        assertThat(citationContentBlockLocation.documentIndex()).isEqualTo(0L)
        assertThat(citationContentBlockLocation.documentTitle()).contains("document_title")
        assertThat(citationContentBlockLocation.endBlockIndex()).isEqualTo(0L)
        assertThat(citationContentBlockLocation.startBlockIndex()).isEqualTo(0L)
    }
}
