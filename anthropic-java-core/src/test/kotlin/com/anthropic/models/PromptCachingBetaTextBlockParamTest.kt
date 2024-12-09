// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PromptCachingBetaTextBlockParamTest {

    @Test
    fun createPromptCachingBetaTextBlockParam() {
        val promptCachingBetaTextBlockParam =
            PromptCachingBetaTextBlockParam.builder()
                .text("x")
                .type(PromptCachingBetaTextBlockParam.Type.TEXT)
                .cacheControl(
                    PromptCachingBetaCacheControlEphemeral.builder()
                        .type(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .build()
        assertThat(promptCachingBetaTextBlockParam).isNotNull
        assertThat(promptCachingBetaTextBlockParam.text()).isEqualTo("x")
        assertThat(promptCachingBetaTextBlockParam.type())
            .isEqualTo(PromptCachingBetaTextBlockParam.Type.TEXT)
        assertThat(promptCachingBetaTextBlockParam.cacheControl())
            .contains(
                PromptCachingBetaCacheControlEphemeral.builder()
                    .type(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
                    .build()
            )
    }
}
