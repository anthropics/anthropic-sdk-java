package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationTracesTest {

    @Test
    fun create() {
        val betaTelemetryDestinationTraces =
            BetaTelemetryDestinationTraces.builder()
                .contentMode(BetaTelemetryDestinationContentMode.METADATA_ONLY)
                .enabled(true)
                .endpointUrl("endpoint_url")
                .maxContentBytes(0)
                .build()

        assertThat(betaTelemetryDestinationTraces.contentMode())
            .isEqualTo(BetaTelemetryDestinationContentMode.METADATA_ONLY)
        assertThat(betaTelemetryDestinationTraces.enabled()).isEqualTo(true)
        assertThat(betaTelemetryDestinationTraces.endpointUrl()).contains("endpoint_url")
        assertThat(betaTelemetryDestinationTraces.maxContentBytes()).contains(0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationTraces =
            BetaTelemetryDestinationTraces.builder()
                .contentMode(BetaTelemetryDestinationContentMode.METADATA_ONLY)
                .enabled(true)
                .endpointUrl("endpoint_url")
                .maxContentBytes(0)
                .build()

        val roundtrippedBetaTelemetryDestinationTraces =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationTraces),
                jacksonTypeRef<BetaTelemetryDestinationTraces>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationTraces)
            .isEqualTo(betaTelemetryDestinationTraces)
    }
}
