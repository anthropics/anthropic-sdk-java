// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingConfigEnabledTest {

    @Test
    fun create() {
        val thinkingConfigEnabled = ThinkingConfigEnabled.builder().budgetTokens(1024L).build()

        assertThat(thinkingConfigEnabled.budgetTokens()).isEqualTo(1024L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val thinkingConfigEnabled = ThinkingConfigEnabled.builder().budgetTokens(1024L).build()

        val roundtrippedThinkingConfigEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(thinkingConfigEnabled),
                jacksonTypeRef<ThinkingConfigEnabled>(),
            )

        assertThat(roundtrippedThinkingConfigEnabled).isEqualTo(thinkingConfigEnabled)
    }
}
