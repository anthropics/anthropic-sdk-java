// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFallbackRefusalTriggerTest {

    @Test
    fun create() {
        val betaFallbackRefusalTrigger =
            BetaFallbackRefusalTrigger.builder()
                .category(BetaFallbackRefusalTrigger.Category.CYBER)
                .build()

        assertThat(betaFallbackRefusalTrigger.category())
            .contains(BetaFallbackRefusalTrigger.Category.CYBER)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackRefusalTrigger =
            BetaFallbackRefusalTrigger.builder()
                .category(BetaFallbackRefusalTrigger.Category.CYBER)
                .build()

        val roundtrippedBetaFallbackRefusalTrigger =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackRefusalTrigger),
                jacksonTypeRef<BetaFallbackRefusalTrigger>(),
            )

        assertThat(roundtrippedBetaFallbackRefusalTrigger).isEqualTo(betaFallbackRefusalTrigger)
    }
}
