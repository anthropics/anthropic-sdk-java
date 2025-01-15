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
                .type(BetaToolResultBlockParam.Type.TOOL_RESULT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .content("string")
                .isError(true)
                .build()
        assertThat(betaToolResultBlockParam).isNotNull
        assertThat(betaToolResultBlockParam.toolUseId()).isEqualTo("tool_use_id")
        assertThat(betaToolResultBlockParam.type())
            .isEqualTo(BetaToolResultBlockParam.Type.TOOL_RESULT)
        assertThat(betaToolResultBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                    .build()
            )
        assertThat(betaToolResultBlockParam.content())
            .contains(BetaToolResultBlockParam.Content.ofString("string"))
        assertThat(betaToolResultBlockParam.isError()).contains(true)
    }
}
