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
                .type(TextBlockParam.Type.TEXT)
                .cacheControl(
                    CacheControlEphemeral.builder()
                        .type(CacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .addCitation(
                    CitationCharLocationParam.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("x")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .type(CitationCharLocationParam.Type.CHAR_LOCATION)
                        .build()
                )
                .build()
        assertThat(textBlockParam).isNotNull
        assertThat(textBlockParam.text()).isEqualTo("x")
        assertThat(textBlockParam.type()).isEqualTo(TextBlockParam.Type.TEXT)
        assertThat(textBlockParam.cacheControl())
            .contains(
                CacheControlEphemeral.builder().type(CacheControlEphemeral.Type.EPHEMERAL).build()
            )
        assertThat(textBlockParam.citations().get())
            .containsExactly(
                TextCitationParam.ofCitationCharLocationParam(
                    CitationCharLocationParam.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("x")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .type(CitationCharLocationParam.Type.CHAR_LOCATION)
                        .build()
                )
            )
    }
}
