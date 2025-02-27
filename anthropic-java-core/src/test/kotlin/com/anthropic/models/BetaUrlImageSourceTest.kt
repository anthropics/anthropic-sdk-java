// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaUrlImageSourceTest {

    @Test
    fun createBetaUrlImageSource() {
        val betaUrlImageSource = BetaUrlImageSource.builder().url("url").build()
        assertThat(betaUrlImageSource).isNotNull
        assertThat(betaUrlImageSource.url()).isEqualTo("url")
    }
}
