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
                .maxIterations(3)
                .build()

        assertThat(betaManagedAgentsUserDefineOutcomeEventParams.description())
            .isEqualTo("Produce a 2-page summary as summary.md")
        assertThat(betaManagedAgentsUserDefineOutcomeEventParams.rubric())
            .isEqualTo(
                BetaManagedAgentsUserDefineOutcomeEventParams.Rubric.ofText(
                    "Must cover all five sections; cite sources inline."
                )
            )
        assertThat(betaManagedAgentsUserDefineOutcomeEventParams.maxIterations()).contains(3)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserDefineOutcomeEventParams =
            BetaManagedAgentsUserDefineOutcomeEventParams.builder()
                .description("Produce a 2-page summary as summary.md")
                .textRubric("Must cover all five sections; cite sources inline.")
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
