// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRedactedThinkingBlockTest {

    @Test
    fun create() {
        val betaRedactedThinkingBlock = BetaRedactedThinkingBlock.builder().data("data").build()

        assertThat(betaRedactedThinkingBlock.data()).isEqualTo("data")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRedactedThinkingBlock = BetaRedactedThinkingBlock.builder().data("data").build()

        val roundtrippedBetaRedactedThinkingBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRedactedThinkingBlock),
                jacksonTypeRef<BetaRedactedThinkingBlock>(),
            )

        assertThat(roundtrippedBetaRedactedThinkingBlock).isEqualTo(betaRedactedThinkingBlock)
    }
}
