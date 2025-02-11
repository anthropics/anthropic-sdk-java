// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaTextBlockParamTest {

    @Test
    fun createBetaTextBlockParam() {
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
        assertThat(betaTextBlockParam).isNotNull
        assertThat(betaTextBlockParam.text()).isEqualTo("x")
        assertThat(betaTextBlockParam.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
        assertThat(betaTextBlockParam.citations().get())
            .containsExactly(
                BetaTextCitationParam.ofCitationCharLocation(
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
}
