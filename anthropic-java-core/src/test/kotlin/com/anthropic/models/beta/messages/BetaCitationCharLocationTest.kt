// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationCharLocationTest {

    @Test
    fun create() {
        val betaCitationCharLocation =
            BetaCitationCharLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endCharIndex(0L)
                .fileId("file_id")
                .startCharIndex(0L)
                .build()

        assertThat(betaCitationCharLocation.citedText()).isEqualTo("cited_text")
        assertThat(betaCitationCharLocation.documentIndex()).isEqualTo(0L)
        assertThat(betaCitationCharLocation.documentTitle()).contains("document_title")
        assertThat(betaCitationCharLocation.endCharIndex()).isEqualTo(0L)
        assertThat(betaCitationCharLocation.fileId()).contains("file_id")
        assertThat(betaCitationCharLocation.startCharIndex()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCitationCharLocation =
            BetaCitationCharLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endCharIndex(0L)
                .fileId("file_id")
                .startCharIndex(0L)
                .build()

        val roundtrippedBetaCitationCharLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCitationCharLocation),
                jacksonTypeRef<BetaCitationCharLocation>(),
            )

        assertThat(roundtrippedBetaCitationCharLocation).isEqualTo(betaCitationCharLocation)
    }
}
