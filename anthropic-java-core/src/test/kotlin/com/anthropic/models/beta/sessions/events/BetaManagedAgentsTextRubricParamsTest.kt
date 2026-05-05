// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTextRubricParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsTextRubricParams =
            BetaManagedAgentsTextRubricParams.builder()
                .content("Must cover all five sections; cite sources inline.")
                .type(BetaManagedAgentsTextRubricParams.Type.TEXT)
                .build()

        assertThat(betaManagedAgentsTextRubricParams.content())
            .isEqualTo("Must cover all five sections; cite sources inline.")
        assertThat(betaManagedAgentsTextRubricParams.type())
            .isEqualTo(BetaManagedAgentsTextRubricParams.Type.TEXT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTextRubricParams =
            BetaManagedAgentsTextRubricParams.builder()
                .content("Must cover all five sections; cite sources inline.")
                .type(BetaManagedAgentsTextRubricParams.Type.TEXT)
                .build()

        val roundtrippedBetaManagedAgentsTextRubricParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTextRubricParams),
                jacksonTypeRef<BetaManagedAgentsTextRubricParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTextRubricParams)
            .isEqualTo(betaManagedAgentsTextRubricParams)
    }
}
