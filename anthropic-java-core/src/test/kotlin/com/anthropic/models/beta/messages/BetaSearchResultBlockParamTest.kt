// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSearchResultBlockParamTest {

    @Test
    fun create() {
        val betaSearchResultBlockParam =
            BetaSearchResultBlockParam.builder()
                .addContent(
                    BetaTextBlockParam.builder()
                        .text("x")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .addCitation(
                            BetaCitationCharLocationParam.builder()
                                .citedText("cited_text")
                                .documentIndex(0L)
                                .documentTitle("x")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .build()
                        )
                        .build()
                )
                .source("source")
                .title("title")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .build()

        assertThat(betaSearchResultBlockParam.content())
            .containsExactly(
                BetaTextBlockParam.builder()
                    .text("x")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .addCitation(
                        BetaCitationCharLocationParam.builder()
                            .citedText("cited_text")
                            .documentIndex(0L)
                            .documentTitle("x")
                            .endCharIndex(0L)
                            .startCharIndex(0L)
                            .build()
                    )
                    .build()
            )
        assertThat(betaSearchResultBlockParam.source()).isEqualTo("source")
        assertThat(betaSearchResultBlockParam.title()).isEqualTo("title")
        assertThat(betaSearchResultBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaSearchResultBlockParam.citations())
            .contains(BetaCitationsConfigParam.builder().enabled(true).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSearchResultBlockParam =
            BetaSearchResultBlockParam.builder()
                .addContent(
                    BetaTextBlockParam.builder()
                        .text("x")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .addCitation(
                            BetaCitationCharLocationParam.builder()
                                .citedText("cited_text")
                                .documentIndex(0L)
                                .documentTitle("x")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .build()
                        )
                        .build()
                )
                .source("source")
                .title("title")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .build()

        val roundtrippedBetaSearchResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSearchResultBlockParam),
                jacksonTypeRef<BetaSearchResultBlockParam>(),
            )

        assertThat(roundtrippedBetaSearchResultBlockParam).isEqualTo(betaSearchResultBlockParam)
    }
}
