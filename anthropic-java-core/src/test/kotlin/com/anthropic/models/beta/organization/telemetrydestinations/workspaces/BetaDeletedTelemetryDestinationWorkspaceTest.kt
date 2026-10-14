package com.anthropic.models.beta.organization.telemetrydestinations.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedTelemetryDestinationWorkspaceTest {

    @Test
    fun create() {
        val betaDeletedTelemetryDestinationWorkspace =
            BetaDeletedTelemetryDestinationWorkspace.builder()
                .telemetryDestinationId("telemetry_destination_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaDeletedTelemetryDestinationWorkspace.telemetryDestinationId())
            .isEqualTo("telemetry_destination_id")
        assertThat(betaDeletedTelemetryDestinationWorkspace.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedTelemetryDestinationWorkspace =
            BetaDeletedTelemetryDestinationWorkspace.builder()
                .telemetryDestinationId("telemetry_destination_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaDeletedTelemetryDestinationWorkspace =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedTelemetryDestinationWorkspace),
                jacksonTypeRef<BetaDeletedTelemetryDestinationWorkspace>(),
            )

        assertThat(roundtrippedBetaDeletedTelemetryDestinationWorkspace)
            .isEqualTo(betaDeletedTelemetryDestinationWorkspace)
    }
}
