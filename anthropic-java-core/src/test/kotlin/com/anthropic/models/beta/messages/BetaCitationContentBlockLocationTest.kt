// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationContentBlockLocationTest {

    @Test
    fun create() {
        val betaCitationContentBlockLocation =
            BetaCitationContentBlockLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        assertThat(betaCitationContentBlockLocation.citedText()).isEqualTo("cited_text")
        assertThat(betaCitationContentBlockLocation.documentIndex()).isEqualTo(0L)
        assertThat(betaCitationContentBlockLocation.documentTitle()).contains("document_title")
        assertThat(betaCitationContentBlockLocation.endBlockIndex()).isEqualTo(0L)
        assertThat(betaCitationContentBlockLocation.startBlockIndex()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCitationContentBlockLocation =
            BetaCitationContentBlockLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val roundtrippedBetaCitationContentBlockLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCitationContentBlockLocation),
                jacksonTypeRef<BetaCitationContentBlockLocation>(),
            )

        assertThat(roundtrippedBetaCitationContentBlockLocation)
            .isEqualTo(betaCitationContentBlockLocation)
    }
}
