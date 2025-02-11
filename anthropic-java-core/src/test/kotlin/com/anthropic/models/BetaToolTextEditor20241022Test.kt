// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolTextEditor20241022Test {

    @Test
    fun createBetaToolTextEditor20241022() {
        val betaToolTextEditor20241022 =
            BetaToolTextEditor20241022.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()
        assertThat(betaToolTextEditor20241022).isNotNull
        assertThat(betaToolTextEditor20241022.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }
}
