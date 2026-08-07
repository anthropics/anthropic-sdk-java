// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
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
                .from(BetaFallbackInfoParam.of(Model.CLAUDE_SONNET_5))
                .to(BetaFallbackInfoParam.of(Model.CLAUDE_SONNET_5))
                .trigger(JsonValue.from(mapOf<String, Any>()))
                .build()

        assertThat(betaFallbackBlockParam.from())
            .isEqualTo(BetaFallbackInfoParam.of(Model.CLAUDE_SONNET_5))
        assertThat(betaFallbackBlockParam.to())
            .isEqualTo(BetaFallbackInfoParam.of(Model.CLAUDE_SONNET_5))
        assertThat(betaFallbackBlockParam._trigger())
            .isEqualTo(JsonValue.from(mapOf<String, Any>()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackBlockParam =
            BetaFallbackBlockParam.builder()
                .from(BetaFallbackInfoParam.of(Model.CLAUDE_SONNET_5))
                .to(BetaFallbackInfoParam.of(Model.CLAUDE_SONNET_5))
                .trigger(JsonValue.from(mapOf<String, Any>()))
                .build()

        val roundtrippedBetaFallbackBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackBlockParam),
                jacksonTypeRef<BetaFallbackBlockParam>(),
            )

        assertThat(roundtrippedBetaFallbackBlockParam).isEqualTo(betaFallbackBlockParam)
    }
}
