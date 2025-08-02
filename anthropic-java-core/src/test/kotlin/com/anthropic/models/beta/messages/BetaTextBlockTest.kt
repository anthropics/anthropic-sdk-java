// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
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
                        .fileId("file_id")
                        .startCharIndex(0L)
                        .build()
                )
                .text("text")
                .build()

        assertThat(betaTextBlock.citations().getOrNull())
            .containsExactly(
                BetaTextCitation.ofCharLocation(
                    BetaCitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .fileId("file_id")
                        .startCharIndex(0L)
                        .build()
                )
            )
        assertThat(betaTextBlock.text()).isEqualTo("text")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextBlock =
            BetaTextBlock.builder()
                .addCitation(
                    BetaCitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .fileId("file_id")
                        .startCharIndex(0L)
                        .build()
                )
                .text("text")
                .build()

        val roundtrippedBetaTextBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextBlock),
                jacksonTypeRef<BetaTextBlock>(),
            )

        assertThat(roundtrippedBetaTextBlock).isEqualTo(betaTextBlock)
    }
}
