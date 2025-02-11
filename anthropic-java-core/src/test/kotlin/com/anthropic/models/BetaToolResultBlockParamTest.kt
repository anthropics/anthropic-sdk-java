// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolResultBlockParamTest {

    @Test
    fun createBetaToolResultBlockParam() {
        val betaToolResultBlockParam =
            BetaToolResultBlockParam.builder()
                .toolUseId("tool_use_id")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .content("string")
                .isError(true)
                .build()
        assertThat(betaToolResultBlockParam).isNotNull
        assertThat(betaToolResultBlockParam.toolUseId()).isEqualTo("tool_use_id")
        assertThat(betaToolResultBlockParam.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
        assertThat(betaToolResultBlockParam.content())
            .contains(BetaToolResultBlockParam.Content.ofString("string"))
        assertThat(betaToolResultBlockParam.isError()).contains(true)
    }
}
