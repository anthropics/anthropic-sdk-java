// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationsDeltaTest {

    @Test
    fun create() {
        val betaCitationsDelta =
            BetaCitationsDelta.builder()
                .citation(
                    BetaCitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .fileId("file_id")
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        assertThat(betaCitationsDelta.citation())
            .isEqualTo(
                BetaCitationsDelta.Citation.ofCharLocation(
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
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCitationsDelta =
            BetaCitationsDelta.builder()
                .citation(
                    BetaCitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .fileId("file_id")
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val roundtrippedBetaCitationsDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCitationsDelta),
                jacksonTypeRef<BetaCitationsDelta>(),
            )

        assertThat(roundtrippedBetaCitationsDelta).isEqualTo(betaCitationsDelta)
    }
}
