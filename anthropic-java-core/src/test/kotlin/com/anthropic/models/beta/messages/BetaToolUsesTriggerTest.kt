// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolUsesTriggerTest {

    @Test
    fun create() {
        val betaToolUsesTrigger = BetaToolUsesTrigger.builder().value(1L).build()

        assertThat(betaToolUsesTrigger.value()).isEqualTo(1L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUsesTrigger = BetaToolUsesTrigger.builder().value(1L).build()

        val roundtrippedBetaToolUsesTrigger =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUsesTrigger),
                jacksonTypeRef<BetaToolUsesTrigger>(),
            )

        assertThat(roundtrippedBetaToolUsesTrigger).isEqualTo(betaToolUsesTrigger)
    }
}
