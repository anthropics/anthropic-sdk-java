// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UsageTest {

    @Test
    fun create() {
        val usage =
            Usage.builder()
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inputTokens(2095L)
                .outputTokens(503L)
                .build()

        assertThat(usage.cacheCreationInputTokens()).contains(2051L)
        assertThat(usage.cacheReadInputTokens()).contains(2051L)
        assertThat(usage.inputTokens()).isEqualTo(2095L)
        assertThat(usage.outputTokens()).isEqualTo(503L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val usage =
            Usage.builder()
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inputTokens(2095L)
                .outputTokens(503L)
                .build()

        val roundtrippedUsage =
            jsonMapper.readValue(jsonMapper.writeValueAsString(usage), jacksonTypeRef<Usage>())

        assertThat(roundtrippedUsage).isEqualTo(usage)
    }
}
