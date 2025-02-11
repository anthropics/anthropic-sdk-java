// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaCitationCharLocationParamTest {

    @Test
    fun createBetaCitationCharLocationParam() {
        val betaCitationCharLocationParam =
            BetaCitationCharLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()
        assertThat(betaCitationCharLocationParam).isNotNull
        assertThat(betaCitationCharLocationParam.citedText()).isEqualTo("cited_text")
        assertThat(betaCitationCharLocationParam.documentIndex()).isEqualTo(0L)
        assertThat(betaCitationCharLocationParam.documentTitle()).contains("x")
        assertThat(betaCitationCharLocationParam.endCharIndex()).isEqualTo(0L)
        assertThat(betaCitationCharLocationParam.startCharIndex()).isEqualTo(0L)
    }
}
