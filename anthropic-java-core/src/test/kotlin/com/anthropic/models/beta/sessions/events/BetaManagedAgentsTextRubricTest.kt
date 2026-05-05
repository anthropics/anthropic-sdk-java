// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTextRubricTest {

    @Test
    fun create() {
        val betaManagedAgentsTextRubric =
            BetaManagedAgentsTextRubric.builder()
                .content("content")
                .type(BetaManagedAgentsTextRubric.Type.TEXT)
                .build()

        assertThat(betaManagedAgentsTextRubric.content()).isEqualTo("content")
        assertThat(betaManagedAgentsTextRubric.type())
            .isEqualTo(BetaManagedAgentsTextRubric.Type.TEXT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTextRubric =
            BetaManagedAgentsTextRubric.builder()
                .content("content")
                .type(BetaManagedAgentsTextRubric.Type.TEXT)
                .build()

        val roundtrippedBetaManagedAgentsTextRubric =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTextRubric),
                jacksonTypeRef<BetaManagedAgentsTextRubric>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTextRubric).isEqualTo(betaManagedAgentsTextRubric)
    }
}
