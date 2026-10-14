package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationDisabledReasonInvalidAddressTest {

    @Test
    fun create() {
        val betaTelemetryDestinationDisabledReasonInvalidAddress =
            BetaTelemetryDestinationDisabledReasonInvalidAddress.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationDisabledReasonInvalidAddress =
            BetaTelemetryDestinationDisabledReasonInvalidAddress.builder().build()

        val roundtrippedBetaTelemetryDestinationDisabledReasonInvalidAddress =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationDisabledReasonInvalidAddress),
                jacksonTypeRef<BetaTelemetryDestinationDisabledReasonInvalidAddress>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationDisabledReasonInvalidAddress)
            .isEqualTo(betaTelemetryDestinationDisabledReasonInvalidAddress)
    }
}
