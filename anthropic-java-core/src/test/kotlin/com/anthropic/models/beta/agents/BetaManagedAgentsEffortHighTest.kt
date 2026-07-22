// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEffortHighTest {

    @Test
    fun create() {
        val betaManagedAgentsEffortHigh =
            BetaManagedAgentsEffortHigh.builder()
                .type(BetaManagedAgentsEffortHigh.Type.HIGH)
                .build()

        assertThat(betaManagedAgentsEffortHigh.type())
            .isEqualTo(BetaManagedAgentsEffortHigh.Type.HIGH)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEffortHigh =
            BetaManagedAgentsEffortHigh.builder()
                .type(BetaManagedAgentsEffortHigh.Type.HIGH)
                .build()

        val roundtrippedBetaManagedAgentsEffortHigh =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEffortHigh),
                jacksonTypeRef<BetaManagedAgentsEffortHigh>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEffortHigh).isEqualTo(betaManagedAgentsEffortHigh)
    }
}
