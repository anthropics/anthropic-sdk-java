// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFallbackBlockTest {

    @Test
    fun create() {
        val betaFallbackBlock =
            BetaFallbackBlock.builder()
                .from(BetaFallbackInfo.builder().model(Model.CLAUDE_FABLE_5).build())
                .to(BetaFallbackInfo.builder().model(Model.CLAUDE_FABLE_5).build())
                .build()

        assertThat(betaFallbackBlock.from())
            .isEqualTo(BetaFallbackInfo.builder().model(Model.CLAUDE_FABLE_5).build())
        assertThat(betaFallbackBlock.to())
            .isEqualTo(BetaFallbackInfo.builder().model(Model.CLAUDE_FABLE_5).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackBlock =
            BetaFallbackBlock.builder()
                .from(BetaFallbackInfo.builder().model(Model.CLAUDE_FABLE_5).build())
                .to(BetaFallbackInfo.builder().model(Model.CLAUDE_FABLE_5).build())
                .build()

        val roundtrippedBetaFallbackBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackBlock),
                jacksonTypeRef<BetaFallbackBlock>(),
            )

        assertThat(roundtrippedBetaFallbackBlock).isEqualTo(betaFallbackBlock)
    }
}
