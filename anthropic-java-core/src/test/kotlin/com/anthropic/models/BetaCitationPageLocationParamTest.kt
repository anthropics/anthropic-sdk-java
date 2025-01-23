// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaCitationPageLocationParamTest {

    @Test
    fun createBetaCitationPageLocationParam() {
        val betaCitationPageLocationParam =
            BetaCitationPageLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .type(BetaCitationPageLocationParam.Type.PAGE_LOCATION)
                .build()
        assertThat(betaCitationPageLocationParam).isNotNull
        assertThat(betaCitationPageLocationParam.citedText()).isEqualTo("cited_text")
        assertThat(betaCitationPageLocationParam.documentIndex()).isEqualTo(0L)
        assertThat(betaCitationPageLocationParam.documentTitle()).contains("x")
        assertThat(betaCitationPageLocationParam.endPageNumber()).isEqualTo(0L)
        assertThat(betaCitationPageLocationParam.startPageNumber()).isEqualTo(1L)
        assertThat(betaCitationPageLocationParam.type())
            .isEqualTo(BetaCitationPageLocationParam.Type.PAGE_LOCATION)
    }
}
