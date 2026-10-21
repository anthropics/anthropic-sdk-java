package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTextRubricTest {

    @Test
    fun create() {
        val betaManagedAgentsTextRubric = BetaManagedAgentsTextRubric.of("content")

        assertThat(betaManagedAgentsTextRubric.content()).isEqualTo("content")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTextRubric = BetaManagedAgentsTextRubric.of("content")

        val roundtrippedBetaManagedAgentsTextRubric =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTextRubric),
                jacksonTypeRef<BetaManagedAgentsTextRubric>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTextRubric).isEqualTo(betaManagedAgentsTextRubric)
    }
}
