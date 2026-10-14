package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationStateEnabledParamsTest {

    @Test
    fun create() {
        val betaTelemetryDestinationStateEnabledParams =
            BetaTelemetryDestinationStateEnabledParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationStateEnabledParams =
            BetaTelemetryDestinationStateEnabledParams.builder().build()

        val roundtrippedBetaTelemetryDestinationStateEnabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationStateEnabledParams),
                jacksonTypeRef<BetaTelemetryDestinationStateEnabledParams>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationStateEnabledParams)
            .isEqualTo(betaTelemetryDestinationStateEnabledParams)
    }
}
