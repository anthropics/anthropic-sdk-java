// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationPageLocationParamTest {

    @Test
    fun create() {
        val betaCitationPageLocationParam =
            BetaCitationPageLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        assertThat(betaCitationPageLocationParam.citedText()).isEqualTo("cited_text")
        assertThat(betaCitationPageLocationParam.documentIndex()).isEqualTo(0L)
        assertThat(betaCitationPageLocationParam.documentTitle()).contains("x")
        assertThat(betaCitationPageLocationParam.endPageNumber()).isEqualTo(0L)
        assertThat(betaCitationPageLocationParam.startPageNumber()).isEqualTo(1L)
    }
}
