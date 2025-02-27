// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaUrlPdfSourceTest {

    @Test
    fun createBetaUrlPdfSource() {
        val betaUrlPdfSource = BetaUrlPdfSource.builder().url("url").build()
        assertThat(betaUrlPdfSource).isNotNull
        assertThat(betaUrlPdfSource.url()).isEqualTo("url")
    }
}
