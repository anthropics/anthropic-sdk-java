// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSpanOutcomeEvaluationOngoingEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSpanOutcomeEvaluationOngoingEvent =
            BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent.builder()
                .id("sevt_011CZkZbCG2uOpc6xmDfvTzh")
                .iteration(0)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                .type(
                    BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent.Type
                        .SPAN_OUTCOME_EVALUATION_ONGOING
                )
                .build()

        assertThat(betaManagedAgentsSpanOutcomeEvaluationOngoingEvent.id())
            .isEqualTo("sevt_011CZkZbCG2uOpc6xmDfvTzh")
        assertThat(betaManagedAgentsSpanOutcomeEvaluationOngoingEvent.iteration()).isEqualTo(0)
        assertThat(betaManagedAgentsSpanOutcomeEvaluationOngoingEvent.outcomeId())
            .isEqualTo("outc_011CZkZRSw2kEfs6ncTVljxP")
        assertThat(betaManagedAgentsSpanOutcomeEvaluationOngoingEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
        assertThat(betaManagedAgentsSpanOutcomeEvaluationOngoingEvent.type())
            .isEqualTo(
                BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent.Type
                    .SPAN_OUTCOME_EVALUATION_ONGOING
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSpanOutcomeEvaluationOngoingEvent =
            BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent.builder()
                .id("sevt_011CZkZbCG2uOpc6xmDfvTzh")
                .iteration(0)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                .type(
                    BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent.Type
                        .SPAN_OUTCOME_EVALUATION_ONGOING
                )
                .build()

        val roundtrippedBetaManagedAgentsSpanOutcomeEvaluationOngoingEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSpanOutcomeEvaluationOngoingEvent),
                jacksonTypeRef<BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSpanOutcomeEvaluationOngoingEvent)
            .isEqualTo(betaManagedAgentsSpanOutcomeEvaluationOngoingEvent)
    }
}
