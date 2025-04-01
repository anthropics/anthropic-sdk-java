// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
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
