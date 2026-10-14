package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationTestTest {

    @Test
    fun create() {
        val betaTelemetryDestinationTest =
            BetaTelemetryDestinationTest.builder()
                .result(BetaTelemetryDestinationTestSuccess.builder().build())
                .build()

        assertThat(betaTelemetryDestinationTest.result())
            .isEqualTo(
                BetaTelemetryDestinationTestResult.ofSuccess(
                    BetaTelemetryDestinationTestSuccess.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationTest =
            BetaTelemetryDestinationTest.builder()
                .result(BetaTelemetryDestinationTestSuccess.builder().build())
                .build()

        val roundtrippedBetaTelemetryDestinationTest =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationTest),
                jacksonTypeRef<BetaTelemetryDestinationTest>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationTest).isEqualTo(betaTelemetryDestinationTest)
    }
}
