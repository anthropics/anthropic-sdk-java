package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationStateEnabledTest {

    @Test
    fun create() {
        val betaTelemetryDestinationStateEnabled =
            BetaTelemetryDestinationStateEnabled.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationStateEnabled =
            BetaTelemetryDestinationStateEnabled.builder().build()

        val roundtrippedBetaTelemetryDestinationStateEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationStateEnabled),
                jacksonTypeRef<BetaTelemetryDestinationStateEnabled>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationStateEnabled)
            .isEqualTo(betaTelemetryDestinationStateEnabled)
    }
}
