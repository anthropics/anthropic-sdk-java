// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolBash20250124Test {

    @Test
    fun create() {
        val betaToolBash20250124 =
            BetaToolBash20250124.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        assertThat(betaToolBash20250124.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }
}
