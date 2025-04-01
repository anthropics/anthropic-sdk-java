// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingBlockTest {

    @Test
    fun create() {
        val thinkingBlock =
            ThinkingBlock.builder().signature("signature").thinking("thinking").build()

        assertThat(thinkingBlock.signature()).isEqualTo("signature")
        assertThat(thinkingBlock.thinking()).isEqualTo("thinking")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val thinkingBlock =
            ThinkingBlock.builder().signature("signature").thinking("thinking").build()

        val roundtrippedThinkingBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(thinkingBlock),
                jacksonTypeRef<ThinkingBlock>(),
            )

        assertThat(roundtrippedThinkingBlock).isEqualTo(thinkingBlock)
    }
}
