// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PromptCachingBetaUsageTest {

    @Test
    fun createPromptCachingBetaUsage() {
        val promptCachingBetaUsage =
            PromptCachingBetaUsage.builder()
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inputTokens(2095L)
                .outputTokens(503L)
                .build()
        assertThat(promptCachingBetaUsage).isNotNull
        assertThat(promptCachingBetaUsage.cacheCreationInputTokens()).contains(2051L)
        assertThat(promptCachingBetaUsage.cacheReadInputTokens()).contains(2051L)
        assertThat(promptCachingBetaUsage.inputTokens()).isEqualTo(2095L)
        assertThat(promptCachingBetaUsage.outputTokens()).isEqualTo(503L)
    }
}
