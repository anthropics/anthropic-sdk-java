// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RawMessageStopEventTest {

    @Test
    fun create() {
        val rawMessageStopEvent = RawMessageStopEvent.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rawMessageStopEvent = RawMessageStopEvent.builder().build()

        val roundtrippedRawMessageStopEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageStopEvent),
                jacksonTypeRef<RawMessageStopEvent>(),
            )

        assertThat(roundtrippedRawMessageStopEvent).isEqualTo(rawMessageStopEvent)
    }
}
