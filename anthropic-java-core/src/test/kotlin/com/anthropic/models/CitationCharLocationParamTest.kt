// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CitationCharLocationParamTest {

    @Test
    fun createCitationCharLocationParam() {
        val citationCharLocationParam =
            CitationCharLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()
        assertThat(citationCharLocationParam).isNotNull
        assertThat(citationCharLocationParam.citedText()).isEqualTo("cited_text")
        assertThat(citationCharLocationParam.documentIndex()).isEqualTo(0L)
        assertThat(citationCharLocationParam.documentTitle()).contains("x")
        assertThat(citationCharLocationParam.endCharIndex()).isEqualTo(0L)
        assertThat(citationCharLocationParam.startCharIndex()).isEqualTo(0L)
    }
}
