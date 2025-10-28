// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClearThinking20251015EditTest {

    @Test
    fun create() {
        val betaClearThinking20251015Edit =
            BetaClearThinking20251015Edit.builder()
                .keep(BetaThinkingTurns.builder().value(1L).build())
                .build()

        assertThat(betaClearThinking20251015Edit.keep())
            .contains(
                BetaClearThinking20251015Edit.Keep.ofBetaThinkingTurns(
                    BetaThinkingTurns.builder().value(1L).build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClearThinking20251015Edit =
            BetaClearThinking20251015Edit.builder()
                .keep(BetaThinkingTurns.builder().value(1L).build())
                .build()

        val roundtrippedBetaClearThinking20251015Edit =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClearThinking20251015Edit),
                jacksonTypeRef<BetaClearThinking20251015Edit>(),
            )

        assertThat(roundtrippedBetaClearThinking20251015Edit)
            .isEqualTo(betaClearThinking20251015Edit)
    }
}
