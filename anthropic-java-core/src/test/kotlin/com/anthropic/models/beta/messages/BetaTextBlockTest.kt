// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextBlockTest {

    @Test
    fun create() {
        val betaTextBlock =
            BetaTextBlock.builder()
                .addCitation(
                    BetaCitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .text("text")
                .build()

        assertThat(betaTextBlock.citations().getOrNull())
            .containsExactly(
                BetaTextCitation.ofCitationCharLocation(
                    BetaCitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
            )
        assertThat(betaTextBlock.text()).isEqualTo("text")
    }
}
