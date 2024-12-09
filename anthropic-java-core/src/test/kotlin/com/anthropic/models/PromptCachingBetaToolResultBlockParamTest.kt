// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PromptCachingBetaToolResultBlockParamTest {

    @Test
    fun createPromptCachingBetaToolResultBlockParam() {
        val promptCachingBetaToolResultBlockParam =
            PromptCachingBetaToolResultBlockParam.builder()
                .toolUseId("tool_use_id")
                .type(PromptCachingBetaToolResultBlockParam.Type.TOOL_RESULT)
                .cacheControl(
                    PromptCachingBetaCacheControlEphemeral.builder()
                        .type(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .content(PromptCachingBetaToolResultBlockParam.Content.ofString("string"))
                .isError(true)
                .build()
        assertThat(promptCachingBetaToolResultBlockParam).isNotNull
        assertThat(promptCachingBetaToolResultBlockParam.toolUseId()).isEqualTo("tool_use_id")
        assertThat(promptCachingBetaToolResultBlockParam.type())
            .isEqualTo(PromptCachingBetaToolResultBlockParam.Type.TOOL_RESULT)
        assertThat(promptCachingBetaToolResultBlockParam.cacheControl())
            .contains(
                PromptCachingBetaCacheControlEphemeral.builder()
                    .type(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
                    .build()
            )
        assertThat(promptCachingBetaToolResultBlockParam.content())
            .contains(PromptCachingBetaToolResultBlockParam.Content.ofString("string"))
        assertThat(promptCachingBetaToolResultBlockParam.isError()).contains(true)
    }
}
