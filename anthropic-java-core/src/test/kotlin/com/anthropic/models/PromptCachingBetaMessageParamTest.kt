// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PromptCachingBetaMessageParamTest {

    @Test
    fun createPromptCachingBetaMessageParam() {
        val promptCachingBetaMessageParam =
            PromptCachingBetaMessageParam.builder()
                .content(PromptCachingBetaMessageParam.Content.ofString("string"))
                .role(PromptCachingBetaMessageParam.Role.USER)
                .build()
        assertThat(promptCachingBetaMessageParam).isNotNull
        assertThat(promptCachingBetaMessageParam.content())
            .isEqualTo(PromptCachingBetaMessageParam.Content.ofString("string"))
        assertThat(promptCachingBetaMessageParam.role())
            .isEqualTo(PromptCachingBetaMessageParam.Role.USER)
    }
}
