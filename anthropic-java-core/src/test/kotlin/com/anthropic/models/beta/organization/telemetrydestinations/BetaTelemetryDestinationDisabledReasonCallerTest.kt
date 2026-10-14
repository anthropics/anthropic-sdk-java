package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationDisabledReasonCallerTest {

    @Test
    fun create() {
        val betaTelemetryDestinationDisabledReasonCaller =
            BetaTelemetryDestinationDisabledReasonCaller.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationDisabledReasonCaller =
            BetaTelemetryDestinationDisabledReasonCaller.builder().build()

        val roundtrippedBetaTelemetryDestinationDisabledReasonCaller =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationDisabledReasonCaller),
                jacksonTypeRef<BetaTelemetryDestinationDisabledReasonCaller>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationDisabledReasonCaller)
            .isEqualTo(betaTelemetryDestinationDisabledReasonCaller)
    }
}
