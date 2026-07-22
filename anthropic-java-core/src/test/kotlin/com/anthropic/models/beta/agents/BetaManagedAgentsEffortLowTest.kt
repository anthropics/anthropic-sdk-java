// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEffortLowTest {

    @Test
    fun create() {
        val betaManagedAgentsEffortLow =
            BetaManagedAgentsEffortLow.builder().type(BetaManagedAgentsEffortLow.Type.LOW).build()

        assertThat(betaManagedAgentsEffortLow.type()).isEqualTo(BetaManagedAgentsEffortLow.Type.LOW)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEffortLow =
            BetaManagedAgentsEffortLow.builder().type(BetaManagedAgentsEffortLow.Type.LOW).build()

        val roundtrippedBetaManagedAgentsEffortLow =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEffortLow),
                jacksonTypeRef<BetaManagedAgentsEffortLow>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEffortLow).isEqualTo(betaManagedAgentsEffortLow)
    }
}
