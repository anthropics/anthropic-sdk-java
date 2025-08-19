// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextBlockParamTest {

    @Test
    fun create() {
        val textBlockParam =
            TextBlockParam.builder()
                .text("x")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
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

        assertThat(textBlockParam.text()).isEqualTo("x")
        assertThat(textBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(textBlockParam.citations().getOrNull())
            .containsExactly(
                TextCitationParam.ofCharLocation(
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

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textBlockParam =
            TextBlockParam.builder()
                .text("x")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
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

        val roundtrippedTextBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textBlockParam),
                jacksonTypeRef<TextBlockParam>(),
            )

        assertThat(roundtrippedTextBlockParam).isEqualTo(textBlockParam)
    }
}
