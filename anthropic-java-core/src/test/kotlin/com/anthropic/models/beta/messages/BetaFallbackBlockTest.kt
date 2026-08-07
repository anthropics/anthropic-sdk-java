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
                .from(BetaFallbackInfo.of(Model.CLAUDE_SONNET_5))
                .to(BetaFallbackInfo.of(Model.CLAUDE_SONNET_5))
                .trigger(BetaFallbackRefusalTrigger.of(BetaFallbackRefusalTrigger.Category.CYBER))
                .build()

        assertThat(betaFallbackBlock.from()).isEqualTo(BetaFallbackInfo.of(Model.CLAUDE_SONNET_5))
        assertThat(betaFallbackBlock.to()).isEqualTo(BetaFallbackInfo.of(Model.CLAUDE_SONNET_5))
        assertThat(betaFallbackBlock.trigger())
            .isEqualTo(BetaFallbackRefusalTrigger.of(BetaFallbackRefusalTrigger.Category.CYBER))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackBlock =
            BetaFallbackBlock.builder()
                .from(BetaFallbackInfo.of(Model.CLAUDE_SONNET_5))
                .to(BetaFallbackInfo.of(Model.CLAUDE_SONNET_5))
                .trigger(BetaFallbackRefusalTrigger.of(BetaFallbackRefusalTrigger.Category.CYBER))
                .build()

        val roundtrippedBetaFallbackBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackBlock),
                jacksonTypeRef<BetaFallbackBlock>(),
            )

        assertThat(roundtrippedBetaFallbackBlock).isEqualTo(betaFallbackBlock)
    }
}
