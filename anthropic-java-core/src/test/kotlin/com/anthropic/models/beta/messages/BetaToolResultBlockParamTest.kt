// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolResultBlockParamTest {

    @Test
    fun create() {
        val betaToolResultBlockParam =
            BetaToolResultBlockParam.builder()
                .toolUseId("tool_use_id")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .content("string")
                .isError(true)
                .build()

        assertThat(betaToolResultBlockParam.toolUseId()).isEqualTo("tool_use_id")
        assertThat(betaToolResultBlockParam.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
        assertThat(betaToolResultBlockParam.content())
            .contains(BetaToolResultBlockParam.Content.ofString("string"))
        assertThat(betaToolResultBlockParam.isError()).contains(true)
    }
}
