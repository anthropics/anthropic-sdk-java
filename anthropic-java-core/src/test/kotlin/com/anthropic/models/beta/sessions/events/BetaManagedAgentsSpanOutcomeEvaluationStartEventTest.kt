// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSpanOutcomeEvaluationStartEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSpanOutcomeEvaluationStartEvent =
            BetaManagedAgentsSpanOutcomeEvaluationStartEvent.builder()
                .id("sevt_011CZkZTUy4mGhu8peVXnlzr")
                .iteration(0)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                .type(
                    BetaManagedAgentsSpanOutcomeEvaluationStartEvent.Type
                        .SPAN_OUTCOME_EVALUATION_START
                )
                .build()

        assertThat(betaManagedAgentsSpanOutcomeEvaluationStartEvent.id())
            .isEqualTo("sevt_011CZkZTUy4mGhu8peVXnlzr")
        assertThat(betaManagedAgentsSpanOutcomeEvaluationStartEvent.iteration()).isEqualTo(0)
        assertThat(betaManagedAgentsSpanOutcomeEvaluationStartEvent.outcomeId())
            .isEqualTo("outc_011CZkZRSw2kEfs6ncTVljxP")
        assertThat(betaManagedAgentsSpanOutcomeEvaluationStartEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
        assertThat(betaManagedAgentsSpanOutcomeEvaluationStartEvent.type())
            .isEqualTo(
                BetaManagedAgentsSpanOutcomeEvaluationStartEvent.Type.SPAN_OUTCOME_EVALUATION_START
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSpanOutcomeEvaluationStartEvent =
            BetaManagedAgentsSpanOutcomeEvaluationStartEvent.builder()
                .id("sevt_011CZkZTUy4mGhu8peVXnlzr")
                .iteration(0)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                .type(
                    BetaManagedAgentsSpanOutcomeEvaluationStartEvent.Type
                        .SPAN_OUTCOME_EVALUATION_START
                )
                .build()

        val roundtrippedBetaManagedAgentsSpanOutcomeEvaluationStartEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSpanOutcomeEvaluationStartEvent),
                jacksonTypeRef<BetaManagedAgentsSpanOutcomeEvaluationStartEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSpanOutcomeEvaluationStartEvent)
            .isEqualTo(betaManagedAgentsSpanOutcomeEvaluationStartEvent)
    }
}
