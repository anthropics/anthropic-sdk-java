package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationStateDisabledTest {

    @Test
    fun create() {
        val betaTelemetryDestinationStateDisabled =
            BetaTelemetryDestinationStateDisabled.builder()
                .reason(BetaTelemetryDestinationDisabledReasonCaller.builder().build())
                .build()

        assertThat(betaTelemetryDestinationStateDisabled.reason())
            .isEqualTo(
                BetaTelemetryDestinationDisabledReason.ofCaller(
                    BetaTelemetryDestinationDisabledReasonCaller.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationStateDisabled =
            BetaTelemetryDestinationStateDisabled.builder()
                .reason(BetaTelemetryDestinationDisabledReasonCaller.builder().build())
                .build()

        val roundtrippedBetaTelemetryDestinationStateDisabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationStateDisabled),
                jacksonTypeRef<BetaTelemetryDestinationStateDisabled>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationStateDisabled)
            .isEqualTo(betaTelemetryDestinationStateDisabled)
    }
}
