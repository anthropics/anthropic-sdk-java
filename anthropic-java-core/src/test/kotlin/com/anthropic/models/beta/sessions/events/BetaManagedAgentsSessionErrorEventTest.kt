package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionErrorEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionErrorEvent =
            BetaManagedAgentsSessionErrorEvent.builder()
                .id("id")
                .error(
                    BetaManagedAgentsToolUseLimitReachedError.builder()
                        .message(
                            "This turn stopped because the agent loop reached its limit of 500 tool-use rounds since the turn started or last resumed. A round is one model response that calls tools, plus running those tools. Nothing failed. Send the session a new message to continue; nothing from the stopped turn is discarded."
                        )
                        .retryStatus(BetaManagedAgentsRetryStatusExhausted.builder().build())
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaManagedAgentsSessionErrorEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSessionErrorEvent.error())
            .isEqualTo(
                BetaManagedAgentsSessionErrorEvent.Error.ofToolUseLimitReached(
                    BetaManagedAgentsToolUseLimitReachedError.builder()
                        .message(
                            "This turn stopped because the agent loop reached its limit of 500 tool-use rounds since the turn started or last resumed. A round is one model response that calls tools, plus running those tools. Nothing failed. Send the session a new message to continue; nothing from the stopped turn is discarded."
                        )
                        .retryStatus(BetaManagedAgentsRetryStatusExhausted.builder().build())
                        .build()
                )
            )
        assertThat(betaManagedAgentsSessionErrorEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionErrorEvent =
            BetaManagedAgentsSessionErrorEvent.builder()
                .id("id")
                .error(
                    BetaManagedAgentsToolUseLimitReachedError.builder()
                        .message(
                            "This turn stopped because the agent loop reached its limit of 500 tool-use rounds since the turn started or last resumed. A round is one model response that calls tools, plus running those tools. Nothing failed. Send the session a new message to continue; nothing from the stopped turn is discarded."
                        )
                        .retryStatus(BetaManagedAgentsRetryStatusExhausted.builder().build())
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaManagedAgentsSessionErrorEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionErrorEvent),
                jacksonTypeRef<BetaManagedAgentsSessionErrorEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionErrorEvent)
            .isEqualTo(betaManagedAgentsSessionErrorEvent)
    }
}
