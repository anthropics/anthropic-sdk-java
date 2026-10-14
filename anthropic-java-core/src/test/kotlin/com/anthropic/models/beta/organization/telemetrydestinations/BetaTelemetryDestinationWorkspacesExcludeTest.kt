package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationWorkspacesExcludeTest {

    @Test
    fun create() {
        val betaTelemetryDestinationWorkspacesExclude =
            BetaTelemetryDestinationWorkspacesExclude.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationWorkspacesExclude =
            BetaTelemetryDestinationWorkspacesExclude.builder().build()

        val roundtrippedBetaTelemetryDestinationWorkspacesExclude =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationWorkspacesExclude),
                jacksonTypeRef<BetaTelemetryDestinationWorkspacesExclude>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationWorkspacesExclude)
            .isEqualTo(betaTelemetryDestinationWorkspacesExclude)
    }
}
