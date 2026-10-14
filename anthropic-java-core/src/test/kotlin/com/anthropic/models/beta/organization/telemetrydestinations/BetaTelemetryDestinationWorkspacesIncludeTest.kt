package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationWorkspacesIncludeTest {

    @Test
    fun create() {
        val betaTelemetryDestinationWorkspacesInclude =
            BetaTelemetryDestinationWorkspacesInclude.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationWorkspacesInclude =
            BetaTelemetryDestinationWorkspacesInclude.builder().build()

        val roundtrippedBetaTelemetryDestinationWorkspacesInclude =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationWorkspacesInclude),
                jacksonTypeRef<BetaTelemetryDestinationWorkspacesInclude>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationWorkspacesInclude)
            .isEqualTo(betaTelemetryDestinationWorkspacesInclude)
    }
}
