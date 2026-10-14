package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationTracesParamsTest {

    @Test
    fun create() {
        val betaTelemetryDestinationTracesParams =
            BetaTelemetryDestinationTracesParams.builder()
                .contentMode(BetaTelemetryDestinationContentMode.METADATA_ONLY)
                .enabled(true)
                .endpointUrl("endpoint_url")
                .maxContentBytes(0)
                .build()

        assertThat(betaTelemetryDestinationTracesParams.contentMode())
            .contains(BetaTelemetryDestinationContentMode.METADATA_ONLY)
        assertThat(betaTelemetryDestinationTracesParams.enabled()).contains(true)
        assertThat(betaTelemetryDestinationTracesParams.endpointUrl()).contains("endpoint_url")
        assertThat(betaTelemetryDestinationTracesParams.maxContentBytes()).contains(0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationTracesParams =
            BetaTelemetryDestinationTracesParams.builder()
                .contentMode(BetaTelemetryDestinationContentMode.METADATA_ONLY)
                .enabled(true)
                .endpointUrl("endpoint_url")
                .maxContentBytes(0)
                .build()

        val roundtrippedBetaTelemetryDestinationTracesParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationTracesParams),
                jacksonTypeRef<BetaTelemetryDestinationTracesParams>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationTracesParams)
            .isEqualTo(betaTelemetryDestinationTracesParams)
    }
}
