// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextDeltaTest {

    @Test
    fun create() {
        val betaTextDelta = BetaTextDelta.builder().text("text").build()

        assertThat(betaTextDelta.text()).isEqualTo("text")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextDelta = BetaTextDelta.builder().text("text").build()

        val roundtrippedBetaTextDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextDelta),
                jacksonTypeRef<BetaTextDelta>(),
            )

        assertThat(roundtrippedBetaTextDelta).isEqualTo(betaTextDelta)
    }
}
