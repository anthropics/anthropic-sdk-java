// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRedactedThinkingBlockParamTest {

    @Test
    fun create() {
        val betaRedactedThinkingBlockParam =
            BetaRedactedThinkingBlockParam.builder().data("data").build()

        assertThat(betaRedactedThinkingBlockParam.data()).isEqualTo("data")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRedactedThinkingBlockParam =
            BetaRedactedThinkingBlockParam.builder().data("data").build()

        val roundtrippedBetaRedactedThinkingBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRedactedThinkingBlockParam),
                jacksonTypeRef<BetaRedactedThinkingBlockParam>(),
            )

        assertThat(roundtrippedBetaRedactedThinkingBlockParam)
            .isEqualTo(betaRedactedThinkingBlockParam)
    }
}
