// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UsageTest {

    @Test
    fun createUsage() {
        val usage =
            Usage.builder()
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inputTokens(2095L)
                .outputTokens(503L)
                .build()
        assertThat(usage).isNotNull
        assertThat(usage.cacheCreationInputTokens()).contains(2051L)
        assertThat(usage.cacheReadInputTokens()).contains(2051L)
        assertThat(usage.inputTokens()).isEqualTo(2095L)
        assertThat(usage.outputTokens()).isEqualTo(503L)
    }
}
