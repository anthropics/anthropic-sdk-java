// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CitationPageLocationParamTest {

    @Test
    fun createCitationPageLocationParam() {
        val citationPageLocationParam =
            CitationPageLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()
        assertThat(citationPageLocationParam).isNotNull
        assertThat(citationPageLocationParam.citedText()).isEqualTo("cited_text")
        assertThat(citationPageLocationParam.documentIndex()).isEqualTo(0L)
        assertThat(citationPageLocationParam.documentTitle()).contains("x")
        assertThat(citationPageLocationParam.endPageNumber()).isEqualTo(0L)
        assertThat(citationPageLocationParam.startPageNumber()).isEqualTo(1L)
    }
}
