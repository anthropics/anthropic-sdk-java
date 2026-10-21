package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTextRubricParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsTextRubricParams =
            BetaManagedAgentsTextRubricParams.of(
                "Must cover all five sections; cite sources inline."
            )

        assertThat(betaManagedAgentsTextRubricParams.content())
            .isEqualTo("Must cover all five sections; cite sources inline.")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTextRubricParams =
            BetaManagedAgentsTextRubricParams.of(
                "Must cover all five sections; cite sources inline."
            )

        val roundtrippedBetaManagedAgentsTextRubricParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTextRubricParams),
                jacksonTypeRef<BetaManagedAgentsTextRubricParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTextRubricParams)
            .isEqualTo(betaManagedAgentsTextRubricParams)
    }
}
