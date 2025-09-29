// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolUsesKeepTest {

    @Test
    fun create() {
        val betaToolUsesKeep = BetaToolUsesKeep.builder().value(0L).build()

        assertThat(betaToolUsesKeep.value()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUsesKeep = BetaToolUsesKeep.builder().value(0L).build()

        val roundtrippedBetaToolUsesKeep =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUsesKeep),
                jacksonTypeRef<BetaToolUsesKeep>(),
            )

        assertThat(roundtrippedBetaToolUsesKeep).isEqualTo(betaToolUsesKeep)
    }
}
