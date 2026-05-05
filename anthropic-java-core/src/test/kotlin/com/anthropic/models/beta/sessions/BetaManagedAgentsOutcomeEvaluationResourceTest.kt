// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsOutcomeEvaluationResourceTest {

    @Test
    fun create() {
        val betaManagedAgentsOutcomeEvaluationResource =
            BetaManagedAgentsOutcomeEvaluationResource.builder()
                .completedAt(OffsetDateTime.parse("2026-03-15T10:02:31Z"))
                .description("Produce a 2-page summary as summary.md")
                .explanation("All five sections present with inline citations.")
                .iteration(0)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .result("satisfied")
                .type(BetaManagedAgentsOutcomeEvaluationResource.Type.OUTCOME_EVALUATION)
                .build()

        assertThat(betaManagedAgentsOutcomeEvaluationResource.completedAt())
            .contains(OffsetDateTime.parse("2026-03-15T10:02:31Z"))
        assertThat(betaManagedAgentsOutcomeEvaluationResource.description())
            .isEqualTo("Produce a 2-page summary as summary.md")
        assertThat(betaManagedAgentsOutcomeEvaluationResource.explanation())
            .contains("All five sections present with inline citations.")
        assertThat(betaManagedAgentsOutcomeEvaluationResource.iteration()).isEqualTo(0)
        assertThat(betaManagedAgentsOutcomeEvaluationResource.outcomeId())
            .isEqualTo("outc_011CZkZRSw2kEfs6ncTVljxP")
        assertThat(betaManagedAgentsOutcomeEvaluationResource.result()).isEqualTo("satisfied")
        assertThat(betaManagedAgentsOutcomeEvaluationResource.type())
            .isEqualTo(BetaManagedAgentsOutcomeEvaluationResource.Type.OUTCOME_EVALUATION)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsOutcomeEvaluationResource =
            BetaManagedAgentsOutcomeEvaluationResource.builder()
                .completedAt(OffsetDateTime.parse("2026-03-15T10:02:31Z"))
                .description("Produce a 2-page summary as summary.md")
                .explanation("All five sections present with inline citations.")
                .iteration(0)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVljxP")
                .result("satisfied")
                .type(BetaManagedAgentsOutcomeEvaluationResource.Type.OUTCOME_EVALUATION)
                .build()

        val roundtrippedBetaManagedAgentsOutcomeEvaluationResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsOutcomeEvaluationResource),
                jacksonTypeRef<BetaManagedAgentsOutcomeEvaluationResource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsOutcomeEvaluationResource)
            .isEqualTo(betaManagedAgentsOutcomeEvaluationResource)
    }
}
