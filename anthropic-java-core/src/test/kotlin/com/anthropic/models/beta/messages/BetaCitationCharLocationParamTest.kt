// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationCharLocationParamTest {

    @Test
    fun create() {
        val betaCitationCharLocationParam =
            BetaCitationCharLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("x")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        assertThat(betaCitationCharLocationParam.citedText())
            .isEqualTo("The grass is green. The sky is blue.")
        assertThat(betaCitationCharLocationParam.documentIndex()).isEqualTo(0L)
        assertThat(betaCitationCharLocationParam.documentTitle()).contains("x")
        assertThat(betaCitationCharLocationParam.endCharIndex()).isEqualTo(0L)
        assertThat(betaCitationCharLocationParam.startCharIndex()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCitationCharLocationParam =
            BetaCitationCharLocationParam.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("x")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val roundtrippedBetaCitationCharLocationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCitationCharLocationParam),
                jacksonTypeRef<BetaCitationCharLocationParam>(),
            )

        assertThat(roundtrippedBetaCitationCharLocationParam)
            .isEqualTo(betaCitationCharLocationParam)
    }
}
