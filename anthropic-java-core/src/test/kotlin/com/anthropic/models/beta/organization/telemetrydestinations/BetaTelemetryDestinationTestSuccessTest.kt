package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationTestSuccessTest {

    @Test
    fun create() {
        val betaTelemetryDestinationTestSuccess =
            BetaTelemetryDestinationTestSuccess.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationTestSuccess =
            BetaTelemetryDestinationTestSuccess.builder().build()

        val roundtrippedBetaTelemetryDestinationTestSuccess =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationTestSuccess),
                jacksonTypeRef<BetaTelemetryDestinationTestSuccess>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationTestSuccess)
            .isEqualTo(betaTelemetryDestinationTestSuccess)
    }
}
