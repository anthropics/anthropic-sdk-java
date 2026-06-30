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
                .from(BetaFallbackInfo.builder().model(Model.CLAUDE_SONNET_5).build())
                .to(BetaFallbackInfo.builder().model(Model.CLAUDE_SONNET_5).build())
                .trigger(
                    BetaFallbackRefusalTrigger.builder()
                        .category(BetaFallbackRefusalTrigger.Category.CYBER)
                        .build()
                )
                .build()

        assertThat(betaFallbackBlock.from())
            .isEqualTo(BetaFallbackInfo.builder().model(Model.CLAUDE_SONNET_5).build())
        assertThat(betaFallbackBlock.to())
            .isEqualTo(BetaFallbackInfo.builder().model(Model.CLAUDE_SONNET_5).build())
        assertThat(betaFallbackBlock.trigger())
            .isEqualTo(
                BetaFallbackRefusalTrigger.builder()
                    .category(BetaFallbackRefusalTrigger.Category.CYBER)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackBlock =
            BetaFallbackBlock.builder()
                .from(BetaFallbackInfo.builder().model(Model.CLAUDE_SONNET_5).build())
                .to(BetaFallbackInfo.builder().model(Model.CLAUDE_SONNET_5).build())
                .trigger(
                    BetaFallbackRefusalTrigger.builder()
                        .category(BetaFallbackRefusalTrigger.Category.CYBER)
                        .build()
                )
                .build()

        val roundtrippedBetaFallbackBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackBlock),
                jacksonTypeRef<BetaFallbackBlock>(),
            )

        assertThat(roundtrippedBetaFallbackBlock).isEqualTo(betaFallbackBlock)
    }
}
