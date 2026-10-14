package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationTestFailureTest {

    @Test
    fun create() {
        val betaTelemetryDestinationTestFailure =
            BetaTelemetryDestinationTestFailure.of("HTTP error (status 403)")

        assertThat(betaTelemetryDestinationTestFailure.error()).isEqualTo("HTTP error (status 403)")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationTestFailure =
            BetaTelemetryDestinationTestFailure.of("HTTP error (status 403)")

        val roundtrippedBetaTelemetryDestinationTestFailure =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationTestFailure),
                jacksonTypeRef<BetaTelemetryDestinationTestFailure>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationTestFailure)
            .isEqualTo(betaTelemetryDestinationTestFailure)
    }
}
