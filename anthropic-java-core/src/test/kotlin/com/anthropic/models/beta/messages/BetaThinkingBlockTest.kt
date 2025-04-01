// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingBlockTest {

    @Test
    fun create() {
        val betaThinkingBlock =
            BetaThinkingBlock.builder().signature("signature").thinking("thinking").build()

        assertThat(betaThinkingBlock.signature()).isEqualTo("signature")
        assertThat(betaThinkingBlock.thinking()).isEqualTo("thinking")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingBlock =
            BetaThinkingBlock.builder().signature("signature").thinking("thinking").build()

        val roundtrippedBetaThinkingBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingBlock),
                jacksonTypeRef<BetaThinkingBlock>(),
            )

        assertThat(roundtrippedBetaThinkingBlock).isEqualTo(betaThinkingBlock)
    }
}
