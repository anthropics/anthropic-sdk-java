// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UsageTest {

    @Test
    fun createUsage() {
        val usage = Usage.builder().inputTokens(2095L).outputTokens(503L).build()
        assertThat(usage).isNotNull
        assertThat(usage.inputTokens()).isEqualTo(2095L)
        assertThat(usage.outputTokens()).isEqualTo(503L)
    }
}
