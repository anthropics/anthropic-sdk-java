package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationDeliveryHealthTest {

    @Test
    fun create() {
        val betaTelemetryDestinationDeliveryHealth =
            BetaTelemetryDestinationDeliveryHealth.builder()
                .lastError("last_error")
                .lastErrorAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .lastSuccessAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaTelemetryDestinationDeliveryHealth.lastError()).contains("last_error")
        assertThat(betaTelemetryDestinationDeliveryHealth.lastErrorAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaTelemetryDestinationDeliveryHealth.lastSuccessAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationDeliveryHealth =
            BetaTelemetryDestinationDeliveryHealth.builder()
                .lastError("last_error")
                .lastErrorAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .lastSuccessAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaTelemetryDestinationDeliveryHealth =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationDeliveryHealth),
                jacksonTypeRef<BetaTelemetryDestinationDeliveryHealth>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationDeliveryHealth)
            .isEqualTo(betaTelemetryDestinationDeliveryHealth)
    }
}
