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
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endBlockIndex(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .startBlockIndex(0L)
                .build()

        assertThat(betaCitationContentBlockLocation.citedText())
            .isEqualTo("The grass is green. The sky is blue.")
        assertThat(betaCitationContentBlockLocation.documentIndex()).isEqualTo(0L)
        assertThat(betaCitationContentBlockLocation.documentTitle()).contains("My Document")
        assertThat(betaCitationContentBlockLocation.endBlockIndex()).isEqualTo(0L)
        assertThat(betaCitationContentBlockLocation.fileId())
            .contains("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(betaCitationContentBlockLocation.startBlockIndex()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCitationContentBlockLocation =
            BetaCitationContentBlockLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endBlockIndex(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
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
