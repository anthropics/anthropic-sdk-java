package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationDisabledReasonSustainedFailureTest {

    @Test
    fun create() {
        val betaTelemetryDestinationDisabledReasonSustainedFailure =
            BetaTelemetryDestinationDisabledReasonSustainedFailure.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationDisabledReasonSustainedFailure =
            BetaTelemetryDestinationDisabledReasonSustainedFailure.builder().build()

        val roundtrippedBetaTelemetryDestinationDisabledReasonSustainedFailure =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaTelemetryDestinationDisabledReasonSustainedFailure
                ),
                jacksonTypeRef<BetaTelemetryDestinationDisabledReasonSustainedFailure>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationDisabledReasonSustainedFailure)
            .isEqualTo(betaTelemetryDestinationDisabledReasonSustainedFailure)
    }
}
