// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaCacheControlEphemeralTest {

    @Test
    fun createBetaCacheControlEphemeral() {
        val betaCacheControlEphemeral =
            BetaCacheControlEphemeral.builder()
                .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                .build()
        assertThat(betaCacheControlEphemeral).isNotNull
        assertThat(betaCacheControlEphemeral.type())
            .isEqualTo(BetaCacheControlEphemeral.Type.EPHEMERAL)
    }
}
