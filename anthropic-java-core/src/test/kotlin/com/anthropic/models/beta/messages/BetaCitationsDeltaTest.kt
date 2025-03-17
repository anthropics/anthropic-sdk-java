// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationsDeltaTest {

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
                        .build()
                )
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
                        .build()
                )
            )
    }
}
