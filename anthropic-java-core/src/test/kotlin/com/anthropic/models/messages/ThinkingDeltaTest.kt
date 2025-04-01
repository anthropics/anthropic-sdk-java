// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingDeltaTest {

    @Test
    fun create() {
        val thinkingDelta = ThinkingDelta.builder().thinking("thinking").build()

        assertThat(thinkingDelta.thinking()).isEqualTo("thinking")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val thinkingDelta = ThinkingDelta.builder().thinking("thinking").build()

        val roundtrippedThinkingDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(thinkingDelta),
                jacksonTypeRef<ThinkingDelta>(),
            )

        assertThat(roundtrippedThinkingDelta).isEqualTo(thinkingDelta)
    }
}
