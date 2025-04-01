// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingBlockParamTest {

    @Test
    fun create() {
        val betaThinkingBlockParam =
            BetaThinkingBlockParam.builder().signature("signature").thinking("thinking").build()

        assertThat(betaThinkingBlockParam.signature()).isEqualTo("signature")
        assertThat(betaThinkingBlockParam.thinking()).isEqualTo("thinking")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingBlockParam =
            BetaThinkingBlockParam.builder().signature("signature").thinking("thinking").build()

        val roundtrippedBetaThinkingBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingBlockParam),
                jacksonTypeRef<BetaThinkingBlockParam>(),
            )

        assertThat(roundtrippedBetaThinkingBlockParam).isEqualTo(betaThinkingBlockParam)
    }
}
