// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSpanModelRequestStartEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSpanModelRequestStartEvent =
            BetaManagedAgentsSpanModelRequestStartEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSpanModelRequestStartEvent.Type.SPAN_MODEL_REQUEST_START)
                .build()

        assertThat(betaManagedAgentsSpanModelRequestStartEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSpanModelRequestStartEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsSpanModelRequestStartEvent.type())
            .isEqualTo(BetaManagedAgentsSpanModelRequestStartEvent.Type.SPAN_MODEL_REQUEST_START)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSpanModelRequestStartEvent =
            BetaManagedAgentsSpanModelRequestStartEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSpanModelRequestStartEvent.Type.SPAN_MODEL_REQUEST_START)
                .build()

        val roundtrippedBetaManagedAgentsSpanModelRequestStartEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSpanModelRequestStartEvent),
                jacksonTypeRef<BetaManagedAgentsSpanModelRequestStartEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSpanModelRequestStartEvent)
            .isEqualTo(betaManagedAgentsSpanModelRequestStartEvent)
    }
}
