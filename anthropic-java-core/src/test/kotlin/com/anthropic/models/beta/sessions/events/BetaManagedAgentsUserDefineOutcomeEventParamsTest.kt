// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserDefineOutcomeEventParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsUserDefineOutcomeEventParams =
            BetaManagedAgentsUserDefineOutcomeEventParams.builder()
                .description("Produce a 2-page summary as summary.md")
                .textRubric("Must cover all five sections; cite sources inline.")
                .type(BetaManagedAgentsUserDefineOutcomeEventParams.Type.USER_DEFINE_OUTCOME)
                .maxIterations(3)
                .build()

        assertThat(betaManagedAgentsUserDefineOutcomeEventParams.description())
            .isEqualTo("Produce a 2-page summary as summary.md")
        assertThat(betaManagedAgentsUserDefineOutcomeEventParams.rubric())
            .isEqualTo(
                BetaManagedAgentsUserDefineOutcomeEventParams.Rubric.ofText(
                    BetaManagedAgentsTextRubricParams.builder()
                        .content("Must cover all five sections; cite sources inline.")
                        .type(BetaManagedAgentsTextRubricParams.Type.TEXT)
                        .build()
                )
            )
        assertThat(betaManagedAgentsUserDefineOutcomeEventParams.type())
            .isEqualTo(BetaManagedAgentsUserDefineOutcomeEventParams.Type.USER_DEFINE_OUTCOME)
        assertThat(betaManagedAgentsUserDefineOutcomeEventParams.maxIterations()).contains(3)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserDefineOutcomeEventParams =
            BetaManagedAgentsUserDefineOutcomeEventParams.builder()
                .description("Produce a 2-page summary as summary.md")
                .textRubric("Must cover all five sections; cite sources inline.")
                .type(BetaManagedAgentsUserDefineOutcomeEventParams.Type.USER_DEFINE_OUTCOME)
                .maxIterations(3)
                .build()

        val roundtrippedBetaManagedAgentsUserDefineOutcomeEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserDefineOutcomeEventParams),
                jacksonTypeRef<BetaManagedAgentsUserDefineOutcomeEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserDefineOutcomeEventParams)
            .isEqualTo(betaManagedAgentsUserDefineOutcomeEventParams)
    }
}
