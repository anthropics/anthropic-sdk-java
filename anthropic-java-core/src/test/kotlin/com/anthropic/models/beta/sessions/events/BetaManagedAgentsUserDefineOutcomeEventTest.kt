// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserDefineOutcomeEventTest {

    @Test
    fun create() {
        val betaManagedAgentsUserDefineOutcomeEvent =
            BetaManagedAgentsUserDefineOutcomeEvent.builder()
                .id("sevt_011CZkZSTx3lFgt7odUWmkyq")
                .description("Produce a 2-page summary as summary.md")
                .maxIterations(3)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                .textRubric("Must cover all five sections; cite sources inline.")
                .type(BetaManagedAgentsUserDefineOutcomeEvent.Type.USER_DEFINE_OUTCOME)
                .build()

        assertThat(betaManagedAgentsUserDefineOutcomeEvent.id())
            .isEqualTo("sevt_011CZkZSTx3lFgt7odUWmkyq")
        assertThat(betaManagedAgentsUserDefineOutcomeEvent.description())
            .isEqualTo("Produce a 2-page summary as summary.md")
        assertThat(betaManagedAgentsUserDefineOutcomeEvent.maxIterations()).contains(3)
        assertThat(betaManagedAgentsUserDefineOutcomeEvent.outcomeId())
            .isEqualTo("outc_011CZkZRSw2kEfs6ncTVljxP")
        assertThat(betaManagedAgentsUserDefineOutcomeEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
        assertThat(betaManagedAgentsUserDefineOutcomeEvent.rubric())
            .isEqualTo(
                BetaManagedAgentsUserDefineOutcomeEvent.Rubric.ofText(
                    BetaManagedAgentsTextRubric.builder()
                        .content("Must cover all five sections; cite sources inline.")
                        .type(BetaManagedAgentsTextRubric.Type.TEXT)
                        .build()
                )
            )
        assertThat(betaManagedAgentsUserDefineOutcomeEvent.type())
            .isEqualTo(BetaManagedAgentsUserDefineOutcomeEvent.Type.USER_DEFINE_OUTCOME)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserDefineOutcomeEvent =
            BetaManagedAgentsUserDefineOutcomeEvent.builder()
                .id("sevt_011CZkZSTx3lFgt7odUWmkyq")
                .description("Produce a 2-page summary as summary.md")
                .maxIterations(3)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                .textRubric("Must cover all five sections; cite sources inline.")
                .type(BetaManagedAgentsUserDefineOutcomeEvent.Type.USER_DEFINE_OUTCOME)
                .build()

        val roundtrippedBetaManagedAgentsUserDefineOutcomeEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserDefineOutcomeEvent),
                jacksonTypeRef<BetaManagedAgentsUserDefineOutcomeEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserDefineOutcomeEvent)
            .isEqualTo(betaManagedAgentsUserDefineOutcomeEvent)
    }
}
