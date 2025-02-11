// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TextBlockParamTest {

    @Test
    fun createTextBlockParam() {
        val textBlockParam =
            TextBlockParam.builder()
                .text("x")
                .cacheControl(CacheControlEphemeral.builder().build())
                .addCitation(
                    CitationCharLocationParam.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("x")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .build()
        assertThat(textBlockParam).isNotNull
        assertThat(textBlockParam.text()).isEqualTo("x")
        assertThat(textBlockParam.cacheControl()).contains(CacheControlEphemeral.builder().build())
        assertThat(textBlockParam.citations().get())
            .containsExactly(
                TextCitationParam.ofCitationCharLocation(
                    CitationCharLocationParam.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("x")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
            )
    }
}
