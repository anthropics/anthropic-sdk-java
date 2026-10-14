package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationTestResponseTest {

    @Test
    fun create() {
        val betaTelemetryDestinationTestResponse =
            BetaTelemetryDestinationTestResponse.builder()
                .result(BetaTelemetryDestinationTestSuccess.builder().build())
                .build()

        assertThat(betaTelemetryDestinationTestResponse.result())
            .isEqualTo(
                BetaTelemetryDestinationTestResult.ofSuccess(
                    BetaTelemetryDestinationTestSuccess.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationTestResponse =
            BetaTelemetryDestinationTestResponse.builder()
                .result(BetaTelemetryDestinationTestSuccess.builder().build())
                .build()

        val roundtrippedBetaTelemetryDestinationTestResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationTestResponse),
                jacksonTypeRef<BetaTelemetryDestinationTestResponse>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationTestResponse)
            .isEqualTo(betaTelemetryDestinationTestResponse)
    }
}
