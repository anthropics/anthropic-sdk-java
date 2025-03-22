// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPlainTextSourceTest {

    @Test
    fun create() {
        val betaPlainTextSource = BetaPlainTextSource.builder().data("data").build()

        assertThat(betaPlainTextSource.data()).isEqualTo("data")
    }
}
