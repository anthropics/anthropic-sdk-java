// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolBash20250124Test {

    @Test
    fun createBetaToolBash20250124() {
        val betaToolBash20250124 =
            BetaToolBash20250124.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()
        assertThat(betaToolBash20250124).isNotNull
        assertThat(betaToolBash20250124.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }
}
