package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedTelemetryDestinationTest {

    @Test
    fun create() {
        val betaDeletedTelemetryDestination = BetaDeletedTelemetryDestination.of("id")

        assertThat(betaDeletedTelemetryDestination.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedTelemetryDestination = BetaDeletedTelemetryDestination.of("id")

        val roundtrippedBetaDeletedTelemetryDestination =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedTelemetryDestination),
                jacksonTypeRef<BetaDeletedTelemetryDestination>(),
            )

        assertThat(roundtrippedBetaDeletedTelemetryDestination)
            .isEqualTo(betaDeletedTelemetryDestination)
    }
}
