// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PromptCachingBetaToolUseBlockParamTest {

    @Test
    fun createPromptCachingBetaToolUseBlockParam() {
        val promptCachingBetaToolUseBlockParam =
            PromptCachingBetaToolUseBlockParam.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("x")
                .type(PromptCachingBetaToolUseBlockParam.Type.TOOL_USE)
                .cacheControl(
                    PromptCachingBetaCacheControlEphemeral.builder()
                        .type(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .build()
        assertThat(promptCachingBetaToolUseBlockParam).isNotNull
        assertThat(promptCachingBetaToolUseBlockParam.id()).isEqualTo("id")
        assertThat(promptCachingBetaToolUseBlockParam._input())
            .isEqualTo(JsonValue.from(mapOf<String, Any>()))
        assertThat(promptCachingBetaToolUseBlockParam.name()).isEqualTo("x")
        assertThat(promptCachingBetaToolUseBlockParam.type())
            .isEqualTo(PromptCachingBetaToolUseBlockParam.Type.TOOL_USE)
        assertThat(promptCachingBetaToolUseBlockParam.cacheControl())
            .contains(
                PromptCachingBetaCacheControlEphemeral.builder()
                    .type(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
                    .build()
            )
    }
}
