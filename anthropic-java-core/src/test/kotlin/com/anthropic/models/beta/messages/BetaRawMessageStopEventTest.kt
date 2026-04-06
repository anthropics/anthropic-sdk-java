// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRawMessageStopEventTest {

    @Test
    fun create() {
        val betaRawMessageStopEvent = BetaRawMessageStopEvent.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawMessageStopEvent = BetaRawMessageStopEvent.builder().build()

        val roundtrippedBetaRawMessageStopEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawMessageStopEvent),
                jacksonTypeRef<BetaRawMessageStopEvent>(),
            )

        assertThat(roundtrippedBetaRawMessageStopEvent).isEqualTo(betaRawMessageStopEvent)
    }
}
