// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationPageLocationTest {

    @Test
    fun create() {
        val betaCitationPageLocation =
            BetaCitationPageLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        assertThat(betaCitationPageLocation.citedText()).isEqualTo("cited_text")
        assertThat(betaCitationPageLocation.documentIndex()).isEqualTo(0L)
        assertThat(betaCitationPageLocation.documentTitle()).contains("document_title")
        assertThat(betaCitationPageLocation.endPageNumber()).isEqualTo(0L)
        assertThat(betaCitationPageLocation.startPageNumber()).isEqualTo(1L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCitationPageLocation =
            BetaCitationPageLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val roundtrippedBetaCitationPageLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCitationPageLocation),
                jacksonTypeRef<BetaCitationPageLocation>(),
            )

        assertThat(roundtrippedBetaCitationPageLocation).isEqualTo(betaCitationPageLocation)
    }
}
