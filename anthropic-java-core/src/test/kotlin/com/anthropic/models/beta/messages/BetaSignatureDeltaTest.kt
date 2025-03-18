// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSignatureDeltaTest {

    @Test
    fun create() {
        val betaSignatureDelta = BetaSignatureDelta.builder().signature("signature").build()

        assertThat(betaSignatureDelta.signature()).isEqualTo("signature")
    }
}
