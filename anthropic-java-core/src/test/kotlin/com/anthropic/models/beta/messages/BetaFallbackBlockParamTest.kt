// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFallbackBlockParamTest {

    @Test
    fun create() {
        val betaFallbackBlockParam =
            BetaFallbackBlockParam.builder()
                .from(BetaFallbackInfoParam.builder().model(Model.CLAUDE_FABLE_5).build())
                .to(BetaFallbackInfoParam.builder().model(Model.CLAUDE_FABLE_5).build())
                .build()

        assertThat(betaFallbackBlockParam.from())
            .isEqualTo(BetaFallbackInfoParam.builder().model(Model.CLAUDE_FABLE_5).build())
        assertThat(betaFallbackBlockParam.to())
            .isEqualTo(BetaFallbackInfoParam.builder().model(Model.CLAUDE_FABLE_5).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackBlockParam =
            BetaFallbackBlockParam.builder()
                .from(BetaFallbackInfoParam.builder().model(Model.CLAUDE_FABLE_5).build())
                .to(BetaFallbackInfoParam.builder().model(Model.CLAUDE_FABLE_5).build())
                .build()

        val roundtrippedBetaFallbackBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackBlockParam),
                jacksonTypeRef<BetaFallbackBlockParam>(),
            )

        assertThat(roundtrippedBetaFallbackBlockParam).isEqualTo(betaFallbackBlockParam)
    }
}
