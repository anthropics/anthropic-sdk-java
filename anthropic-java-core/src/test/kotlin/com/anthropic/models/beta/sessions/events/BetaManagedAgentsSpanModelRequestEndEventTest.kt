// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSpanModelRequestEndEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSpanModelRequestEndEvent =
            BetaManagedAgentsSpanModelRequestEndEvent.builder()
                .id("id")
                .isError(true)
                .modelRequestStartId("model_request_start_id")
                .modelUsage(
                    BetaManagedAgentsSpanModelUsage.builder()
                        .cacheCreationInputTokens(0)
                        .cacheReadInputTokens(0)
                        .inputTokens(0)
                        .outputTokens(0)
                        .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSpanModelRequestEndEvent.Type.SPAN_MODEL_REQUEST_END)
                .build()

        assertThat(betaManagedAgentsSpanModelRequestEndEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSpanModelRequestEndEvent.isError()).contains(true)
        assertThat(betaManagedAgentsSpanModelRequestEndEvent.modelRequestStartId())
            .isEqualTo("model_request_start_id")
        assertThat(betaManagedAgentsSpanModelRequestEndEvent.modelUsage())
            .isEqualTo(
                BetaManagedAgentsSpanModelUsage.builder()
                    .cacheCreationInputTokens(0)
                    .cacheReadInputTokens(0)
                    .inputTokens(0)
                    .outputTokens(0)
                    .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                    .build()
            )
        assertThat(betaManagedAgentsSpanModelRequestEndEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsSpanModelRequestEndEvent.type())
            .isEqualTo(BetaManagedAgentsSpanModelRequestEndEvent.Type.SPAN_MODEL_REQUEST_END)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSpanModelRequestEndEvent =
            BetaManagedAgentsSpanModelRequestEndEvent.builder()
                .id("id")
                .isError(true)
                .modelRequestStartId("model_request_start_id")
                .modelUsage(
                    BetaManagedAgentsSpanModelUsage.builder()
                        .cacheCreationInputTokens(0)
                        .cacheReadInputTokens(0)
                        .inputTokens(0)
                        .outputTokens(0)
                        .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSpanModelRequestEndEvent.Type.SPAN_MODEL_REQUEST_END)
                .build()

        val roundtrippedBetaManagedAgentsSpanModelRequestEndEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSpanModelRequestEndEvent),
                jacksonTypeRef<BetaManagedAgentsSpanModelRequestEndEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSpanModelRequestEndEvent)
            .isEqualTo(betaManagedAgentsSpanModelRequestEndEvent)
    }
}
