// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextBlockParamTest {

    @Test
    fun create() {
        val betaTextBlockParam =
            BetaTextBlockParam.builder()
                .text("x")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
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

        assertThat(betaTextBlockParam.text()).isEqualTo("x")
        assertThat(betaTextBlockParam.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
        assertThat(betaTextBlockParam.citations().getOrNull())
            .containsExactly(
                BetaTextCitationParam.ofCharLocation(
                    BetaCitationCharLocationParam.builder()
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
        val betaTextBlockParam =
            BetaTextBlockParam.builder()
                .text("x")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
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

        val roundtrippedBetaTextBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextBlockParam),
                jacksonTypeRef<BetaTextBlockParam>(),
            )

        assertThat(roundtrippedBetaTextBlockParam).isEqualTo(betaTextBlockParam)
    }
}
