// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFallbackInfoParamTest {

    @Test
    fun create() {
        val betaFallbackInfoParam =
            BetaFallbackInfoParam.builder().model(Model.CLAUDE_SONNET_5).build()

        assertThat(betaFallbackInfoParam.model()).isEqualTo(Model.CLAUDE_SONNET_5)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackInfoParam =
            BetaFallbackInfoParam.builder().model(Model.CLAUDE_SONNET_5).build()

        val roundtrippedBetaFallbackInfoParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackInfoParam),
                jacksonTypeRef<BetaFallbackInfoParam>(),
            )

        assertThat(roundtrippedBetaFallbackInfoParam).isEqualTo(betaFallbackInfoParam)
    }
}
