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
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endCharIndex(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .startCharIndex(0L)
                .build()

        assertThat(betaCitationCharLocation.citedText())
            .isEqualTo("The grass is green. The sky is blue.")
        assertThat(betaCitationCharLocation.documentIndex()).isEqualTo(0L)
        assertThat(betaCitationCharLocation.documentTitle()).contains("My Document")
        assertThat(betaCitationCharLocation.endCharIndex()).isEqualTo(0L)
        assertThat(betaCitationCharLocation.fileId()).contains("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(betaCitationCharLocation.startCharIndex()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCitationCharLocation =
            BetaCitationCharLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endCharIndex(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
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
