// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationContentBlockLocationParamTest {

    @Test
    fun create() {
        val citationContentBlockLocationParam =
            CitationContentBlockLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("x")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        assertThat(citationContentBlockLocationParam.citedText())
            .isEqualTo("The grass is green. The sky is blue.")
        assertThat(citationContentBlockLocationParam.documentIndex()).isEqualTo(0L)
        assertThat(citationContentBlockLocationParam.documentTitle()).contains("x")
        assertThat(citationContentBlockLocationParam.endBlockIndex()).isEqualTo(0L)
        assertThat(citationContentBlockLocationParam.startBlockIndex()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationContentBlockLocationParam =
            CitationContentBlockLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("x")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val roundtrippedCitationContentBlockLocationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationContentBlockLocationParam),
                jacksonTypeRef<CitationContentBlockLocationParam>(),
            )

        assertThat(roundtrippedCitationContentBlockLocationParam)
            .isEqualTo(citationContentBlockLocationParam)
    }
}
