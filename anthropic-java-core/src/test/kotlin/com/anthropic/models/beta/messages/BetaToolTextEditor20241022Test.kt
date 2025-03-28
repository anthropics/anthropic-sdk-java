// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolTextEditor20241022Test {

    @Test
    fun create() {
        val betaToolTextEditor20241022 =
            BetaToolTextEditor20241022.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        assertThat(betaToolTextEditor20241022.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }
}
