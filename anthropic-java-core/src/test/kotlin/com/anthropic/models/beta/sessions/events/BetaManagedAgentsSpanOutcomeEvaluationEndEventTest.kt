// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSpanOutcomeEvaluationEndEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSpanOutcomeEvaluationEndEvent =
            BetaManagedAgentsSpanOutcomeEvaluationEndEvent.builder()
                .id("sevt_011CZkZUVz5nHiv9qfWYomas")
                .explanation("All five sections present with inline citations.")
                .iteration(0)
                .outcomeEvaluationStartId("sevt_011CZkZTUy4mGhu8peVXnlzr")
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:31Z"))
                .result("satisfied")
                .type(
                    BetaManagedAgentsSpanOutcomeEvaluationEndEvent.Type.SPAN_OUTCOME_EVALUATION_END
                )
                .usage(
                    BetaManagedAgentsSpanModelUsage.builder()
                        .cacheCreationInputTokens(0)
                        .cacheReadInputTokens(1536)
                        .inputTokens(1842)
                        .outputTokens(213)
                        .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsSpanOutcomeEvaluationEndEvent.id())
            .isEqualTo("sevt_011CZkZUVz5nHiv9qfWYomas")
        assertThat(betaManagedAgentsSpanOutcomeEvaluationEndEvent.explanation())
            .isEqualTo("All five sections present with inline citations.")
        assertThat(betaManagedAgentsSpanOutcomeEvaluationEndEvent.iteration()).isEqualTo(0)
        assertThat(betaManagedAgentsSpanOutcomeEvaluationEndEvent.outcomeEvaluationStartId())
            .isEqualTo("sevt_011CZkZTUy4mGhu8peVXnlzr")
        assertThat(betaManagedAgentsSpanOutcomeEvaluationEndEvent.outcomeId())
            .isEqualTo("outc_011CZkZRSw2kEfs6ncTVljxP")
        assertThat(betaManagedAgentsSpanOutcomeEvaluationEndEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:02:31Z"))
        assertThat(betaManagedAgentsSpanOutcomeEvaluationEndEvent.result()).isEqualTo("satisfied")
        assertThat(betaManagedAgentsSpanOutcomeEvaluationEndEvent.type())
            .isEqualTo(
                BetaManagedAgentsSpanOutcomeEvaluationEndEvent.Type.SPAN_OUTCOME_EVALUATION_END
            )
        assertThat(betaManagedAgentsSpanOutcomeEvaluationEndEvent.usage())
            .isEqualTo(
                BetaManagedAgentsSpanModelUsage.builder()
                    .cacheCreationInputTokens(0)
                    .cacheReadInputTokens(1536)
                    .inputTokens(1842)
                    .outputTokens(213)
                    .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSpanOutcomeEvaluationEndEvent =
            BetaManagedAgentsSpanOutcomeEvaluationEndEvent.builder()
                .id("sevt_011CZkZUVz5nHiv9qfWYomas")
                .explanation("All five sections present with inline citations.")
                .iteration(0)
                .outcomeEvaluationStartId("sevt_011CZkZTUy4mGhu8peVXnlzr")
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:31Z"))
                .result("satisfied")
                .type(
                    BetaManagedAgentsSpanOutcomeEvaluationEndEvent.Type.SPAN_OUTCOME_EVALUATION_END
                )
                .usage(
                    BetaManagedAgentsSpanModelUsage.builder()
                        .cacheCreationInputTokens(0)
                        .cacheReadInputTokens(1536)
                        .inputTokens(1842)
                        .outputTokens(213)
                        .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsSpanOutcomeEvaluationEndEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSpanOutcomeEvaluationEndEvent),
                jacksonTypeRef<BetaManagedAgentsSpanOutcomeEvaluationEndEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSpanOutcomeEvaluationEndEvent)
            .isEqualTo(betaManagedAgentsSpanOutcomeEvaluationEndEvent)
    }
}
