// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingBlockBindingTest {

    @Test
    fun create() {
        val betaThinkingBlockBinding =
            BetaThinkingBlockBinding.builder()
                .prefixMismatchBehavior(BetaThinkingPrefixMismatchBehavior.ERROR)
                .build()

        assertThat(betaThinkingBlockBinding.prefixMismatchBehavior())
            .contains(BetaThinkingPrefixMismatchBehavior.ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingBlockBinding =
            BetaThinkingBlockBinding.builder()
                .prefixMismatchBehavior(BetaThinkingPrefixMismatchBehavior.ERROR)
                .build()

        val roundtrippedBetaThinkingBlockBinding =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingBlockBinding),
                jacksonTypeRef<BetaThinkingBlockBinding>(),
            )

        assertThat(roundtrippedBetaThinkingBlockBinding).isEqualTo(betaThinkingBlockBinding)
    }
}
