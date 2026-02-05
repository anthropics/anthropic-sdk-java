// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCompact20260112EditTest {

    @Test
    fun create() {
        val betaCompact20260112Edit =
            BetaCompact20260112Edit.builder()
                .instructions("instructions")
                .pauseAfterCompaction(true)
                .trigger(BetaInputTokensTrigger.builder().value(1L).build())
                .build()

        assertThat(betaCompact20260112Edit.instructions()).contains("instructions")
        assertThat(betaCompact20260112Edit.pauseAfterCompaction()).contains(true)
        assertThat(betaCompact20260112Edit.trigger())
            .contains(BetaInputTokensTrigger.builder().value(1L).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCompact20260112Edit =
            BetaCompact20260112Edit.builder()
                .instructions("instructions")
                .pauseAfterCompaction(true)
                .trigger(BetaInputTokensTrigger.builder().value(1L).build())
                .build()

        val roundtrippedBetaCompact20260112Edit =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCompact20260112Edit),
                jacksonTypeRef<BetaCompact20260112Edit>(),
            )

        assertThat(roundtrippedBetaCompact20260112Edit).isEqualTo(betaCompact20260112Edit)
    }
}
