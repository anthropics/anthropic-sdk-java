// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInputTokensClearAtLeastTest {

    @Test
    fun create() {
        val betaInputTokensClearAtLeast = BetaInputTokensClearAtLeast.of(0L)

        assertThat(betaInputTokensClearAtLeast.value()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaInputTokensClearAtLeast = BetaInputTokensClearAtLeast.of(0L)

        val roundtrippedBetaInputTokensClearAtLeast =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInputTokensClearAtLeast),
                jacksonTypeRef<BetaInputTokensClearAtLeast>(),
            )

        assertThat(roundtrippedBetaInputTokensClearAtLeast).isEqualTo(betaInputTokensClearAtLeast)
    }
}
