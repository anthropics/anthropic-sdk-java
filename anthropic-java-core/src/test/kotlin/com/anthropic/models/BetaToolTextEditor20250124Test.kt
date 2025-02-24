// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolTextEditor20250124Test {

    @Test
    fun createBetaToolTextEditor20250124() {
        val betaToolTextEditor20250124 =
            BetaToolTextEditor20250124.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()
        assertThat(betaToolTextEditor20250124).isNotNull
        assertThat(betaToolTextEditor20250124.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }
}
