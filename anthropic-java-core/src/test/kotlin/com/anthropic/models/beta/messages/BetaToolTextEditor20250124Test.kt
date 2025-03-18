// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolTextEditor20250124Test {

    @Test
    fun create() {
        val betaToolTextEditor20250124 =
            BetaToolTextEditor20250124.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        assertThat(betaToolTextEditor20250124.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }
}
