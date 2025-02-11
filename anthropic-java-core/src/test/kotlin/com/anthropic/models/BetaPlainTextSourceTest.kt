// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaPlainTextSourceTest {

    @Test
    fun createBetaPlainTextSource() {
        val betaPlainTextSource = BetaPlainTextSource.builder().data("data").build()
        assertThat(betaPlainTextSource).isNotNull
        assertThat(betaPlainTextSource.data()).isEqualTo("data")
    }
}
