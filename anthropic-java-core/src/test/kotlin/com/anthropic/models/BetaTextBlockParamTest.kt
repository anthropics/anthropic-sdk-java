// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaTextBlockParamTest {

    @Test
    fun createBetaTextBlockParam() {
        val betaTextBlockParam =
            BetaTextBlockParam.builder()
                .text("x")
                .type(BetaTextBlockParam.Type.TEXT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .build()
        assertThat(betaTextBlockParam).isNotNull
        assertThat(betaTextBlockParam.text()).isEqualTo("x")
        assertThat(betaTextBlockParam.type()).isEqualTo(BetaTextBlockParam.Type.TEXT)
        assertThat(betaTextBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                    .build()
            )
    }
}
