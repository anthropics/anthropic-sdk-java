// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RawContentBlockStopEventTest {

    @Test
    fun create() {
        val rawContentBlockStopEvent = RawContentBlockStopEvent.builder().index(0L).build()

        assertThat(rawContentBlockStopEvent.index()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rawContentBlockStopEvent = RawContentBlockStopEvent.builder().index(0L).build()

        val roundtrippedRawContentBlockStopEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawContentBlockStopEvent),
                jacksonTypeRef<RawContentBlockStopEvent>(),
            )

        assertThat(roundtrippedRawContentBlockStopEvent).isEqualTo(rawContentBlockStopEvent)
    }
}
