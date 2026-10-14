package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationDisabledReasonRedirectTest {

    @Test
    fun create() {
        val betaTelemetryDestinationDisabledReasonRedirect =
            BetaTelemetryDestinationDisabledReasonRedirect.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationDisabledReasonRedirect =
            BetaTelemetryDestinationDisabledReasonRedirect.builder().build()

        val roundtrippedBetaTelemetryDestinationDisabledReasonRedirect =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationDisabledReasonRedirect),
                jacksonTypeRef<BetaTelemetryDestinationDisabledReasonRedirect>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationDisabledReasonRedirect)
            .isEqualTo(betaTelemetryDestinationDisabledReasonRedirect)
    }
}
