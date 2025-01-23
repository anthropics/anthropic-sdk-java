// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaPlainTextSourceTest {

    @Test
    fun createBetaPlainTextSource() {
        val betaPlainTextSource =
            BetaPlainTextSource.builder()
                .data("data")
                .mediaType(BetaPlainTextSource.MediaType.TEXT_PLAIN)
                .type(BetaPlainTextSource.Type.TEXT)
                .build()
        assertThat(betaPlainTextSource).isNotNull
        assertThat(betaPlainTextSource.data()).isEqualTo("data")
        assertThat(betaPlainTextSource.mediaType())
            .isEqualTo(BetaPlainTextSource.MediaType.TEXT_PLAIN)
        assertThat(betaPlainTextSource.type()).isEqualTo(BetaPlainTextSource.Type.TEXT)
    }
}
