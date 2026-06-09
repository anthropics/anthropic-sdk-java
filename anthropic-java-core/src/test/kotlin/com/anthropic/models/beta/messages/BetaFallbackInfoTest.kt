// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFallbackInfoTest {

    @Test
    fun create() {
        val betaFallbackInfo = BetaFallbackInfo.builder().model(Model.CLAUDE_FABLE_5).build()

        assertThat(betaFallbackInfo.model()).isEqualTo(Model.CLAUDE_FABLE_5)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackInfo = BetaFallbackInfo.builder().model(Model.CLAUDE_FABLE_5).build()

        val roundtrippedBetaFallbackInfo =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackInfo),
                jacksonTypeRef<BetaFallbackInfo>(),
            )

        assertThat(roundtrippedBetaFallbackInfo).isEqualTo(betaFallbackInfo)
    }
}
