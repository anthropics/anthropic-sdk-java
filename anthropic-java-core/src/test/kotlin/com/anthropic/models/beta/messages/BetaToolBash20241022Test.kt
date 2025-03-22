// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolBash20241022Test {

    @Test
    fun create() {
        val betaToolBash20241022 =
            BetaToolBash20241022.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        assertThat(betaToolBash20241022.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }
}
