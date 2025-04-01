// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingBlockParamTest {

    @Test
    fun create() {
        val thinkingBlockParam =
            ThinkingBlockParam.builder().signature("signature").thinking("thinking").build()

        assertThat(thinkingBlockParam.signature()).isEqualTo("signature")
        assertThat(thinkingBlockParam.thinking()).isEqualTo("thinking")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val thinkingBlockParam =
            ThinkingBlockParam.builder().signature("signature").thinking("thinking").build()

        val roundtrippedThinkingBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(thinkingBlockParam),
                jacksonTypeRef<ThinkingBlockParam>(),
            )

        assertThat(roundtrippedThinkingBlockParam).isEqualTo(thinkingBlockParam)
    }
}
