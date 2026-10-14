package com.anthropic.models.beta.organization.telemetrydestinations.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationWorkspaceTest {

    @Test
    fun create() {
        val betaTelemetryDestinationWorkspace =
            BetaTelemetryDestinationWorkspace.builder()
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .telemetryDestinationId("telemetry_destination_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(betaTelemetryDestinationWorkspace.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaTelemetryDestinationWorkspace.telemetryDestinationId())
            .isEqualTo("telemetry_destination_id")
        assertThat(betaTelemetryDestinationWorkspace.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationWorkspace =
            BetaTelemetryDestinationWorkspace.builder()
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .telemetryDestinationId("telemetry_destination_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedBetaTelemetryDestinationWorkspace =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationWorkspace),
                jacksonTypeRef<BetaTelemetryDestinationWorkspace>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationWorkspace)
            .isEqualTo(betaTelemetryDestinationWorkspace)
    }
}
