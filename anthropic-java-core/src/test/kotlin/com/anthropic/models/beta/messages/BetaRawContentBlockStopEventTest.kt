// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRawContentBlockStopEventTest {

    @Test
    fun create() {
        val betaRawContentBlockStopEvent = BetaRawContentBlockStopEvent.builder().index(0L).build()

        assertThat(betaRawContentBlockStopEvent.index()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockStopEvent = BetaRawContentBlockStopEvent.builder().index(0L).build()

        val roundtrippedBetaRawContentBlockStopEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawContentBlockStopEvent),
                jacksonTypeRef<BetaRawContentBlockStopEvent>(),
            )

        assertThat(roundtrippedBetaRawContentBlockStopEvent).isEqualTo(betaRawContentBlockStopEvent)
    }
}
