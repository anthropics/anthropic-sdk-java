// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInputTokensTriggerTest {

    @Test
    fun create() {
        val betaInputTokensTrigger = BetaInputTokensTrigger.builder().value(1L).build()

        assertThat(betaInputTokensTrigger.value()).isEqualTo(1L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaInputTokensTrigger = BetaInputTokensTrigger.builder().value(1L).build()

        val roundtrippedBetaInputTokensTrigger =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInputTokensTrigger),
                jacksonTypeRef<BetaInputTokensTrigger>(),
            )

        assertThat(roundtrippedBetaInputTokensTrigger).isEqualTo(betaInputTokensTrigger)
    }
}
