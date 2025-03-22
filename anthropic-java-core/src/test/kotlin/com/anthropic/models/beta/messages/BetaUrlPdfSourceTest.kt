// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUrlPdfSourceTest {

    @Test
    fun create() {
        val betaUrlPdfSource = BetaUrlPdfSource.builder().url("url").build()

        assertThat(betaUrlPdfSource.url()).isEqualTo("url")
    }
}
