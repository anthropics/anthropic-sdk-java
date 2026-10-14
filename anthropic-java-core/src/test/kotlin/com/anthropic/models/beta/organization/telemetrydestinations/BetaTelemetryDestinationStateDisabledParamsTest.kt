package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationStateDisabledParamsTest {

    @Test
    fun create() {
        val betaTelemetryDestinationStateDisabledParams =
            BetaTelemetryDestinationStateDisabledParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationStateDisabledParams =
            BetaTelemetryDestinationStateDisabledParams.builder().build()

        val roundtrippedBetaTelemetryDestinationStateDisabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationStateDisabledParams),
                jacksonTypeRef<BetaTelemetryDestinationStateDisabledParams>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationStateDisabledParams)
            .isEqualTo(betaTelemetryDestinationStateDisabledParams)
    }
}
