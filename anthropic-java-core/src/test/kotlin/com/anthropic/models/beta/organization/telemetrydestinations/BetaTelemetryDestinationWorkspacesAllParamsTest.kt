package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationWorkspacesAllParamsTest {

    @Test
    fun create() {
        val betaTelemetryDestinationWorkspacesAllParams =
            BetaTelemetryDestinationWorkspacesAllParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationWorkspacesAllParams =
            BetaTelemetryDestinationWorkspacesAllParams.builder().build()

        val roundtrippedBetaTelemetryDestinationWorkspacesAllParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationWorkspacesAllParams),
                jacksonTypeRef<BetaTelemetryDestinationWorkspacesAllParams>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationWorkspacesAllParams)
            .isEqualTo(betaTelemetryDestinationWorkspacesAllParams)
    }
}
