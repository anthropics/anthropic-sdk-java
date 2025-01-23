// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaCitationsDeltaTest {

    @Test
    fun createBetaCitationsDelta() {
        val betaCitationsDelta =
            BetaCitationsDelta.builder()
                .citation(
                    BetaCitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .type(BetaCitationCharLocation.Type.CHAR_LOCATION)
                        .build()
                )
                .type(BetaCitationsDelta.Type.CITATIONS_DELTA)
                .build()
        assertThat(betaCitationsDelta).isNotNull
        assertThat(betaCitationsDelta.citation())
            .isEqualTo(
                BetaCitationsDelta.Citation.ofBetaCitationCharLocation(
                    BetaCitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .type(BetaCitationCharLocation.Type.CHAR_LOCATION)
                        .build()
                )
            )
        assertThat(betaCitationsDelta.type()).isEqualTo(BetaCitationsDelta.Type.CITATIONS_DELTA)
    }
}
