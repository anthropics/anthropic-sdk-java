package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationWorkspacesAllTest {

    @Test
    fun create() {
        val betaTelemetryDestinationWorkspacesAll =
            BetaTelemetryDestinationWorkspacesAll.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationWorkspacesAll =
            BetaTelemetryDestinationWorkspacesAll.builder().build()

        val roundtrippedBetaTelemetryDestinationWorkspacesAll =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationWorkspacesAll),
                jacksonTypeRef<BetaTelemetryDestinationWorkspacesAll>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationWorkspacesAll)
            .isEqualTo(betaTelemetryDestinationWorkspacesAll)
    }
}
