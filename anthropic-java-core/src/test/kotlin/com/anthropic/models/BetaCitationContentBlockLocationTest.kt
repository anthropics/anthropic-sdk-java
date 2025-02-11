// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaCitationContentBlockLocationTest {

    @Test
    fun createBetaCitationContentBlockLocation() {
        val betaCitationContentBlockLocation =
            BetaCitationContentBlockLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()
        assertThat(betaCitationContentBlockLocation).isNotNull
        assertThat(betaCitationContentBlockLocation.citedText()).isEqualTo("cited_text")
        assertThat(betaCitationContentBlockLocation.documentIndex()).isEqualTo(0L)
        assertThat(betaCitationContentBlockLocation.documentTitle()).contains("document_title")
        assertThat(betaCitationContentBlockLocation.endBlockIndex()).isEqualTo(0L)
        assertThat(betaCitationContentBlockLocation.startBlockIndex()).isEqualTo(0L)
    }
}
