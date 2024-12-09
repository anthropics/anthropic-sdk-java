// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaUsageTest {

    @Test
    fun createBetaUsage() {
        val betaUsage =
            BetaUsage.builder()
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inputTokens(2095L)
                .outputTokens(503L)
                .build()
        assertThat(betaUsage).isNotNull
        assertThat(betaUsage.cacheCreationInputTokens()).contains(2051L)
        assertThat(betaUsage.cacheReadInputTokens()).contains(2051L)
        assertThat(betaUsage.inputTokens()).isEqualTo(2095L)
        assertThat(betaUsage.outputTokens()).isEqualTo(503L)
    }
}
