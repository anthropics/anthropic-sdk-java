// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUrlImageSourceTest {

    @Test
    fun create() {
        val betaUrlImageSource = BetaUrlImageSource.builder().url("url").build()

        assertThat(betaUrlImageSource.url()).isEqualTo("url")
    }
}
