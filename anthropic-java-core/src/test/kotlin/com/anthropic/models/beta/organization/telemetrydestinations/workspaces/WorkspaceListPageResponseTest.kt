package com.anthropic.models.beta.organization.telemetrydestinations.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceListPageResponseTest {

    @Test
    fun create() {
        val workspaceListPageResponse =
            WorkspaceListPageResponse.builder()
                .addData(
                    BetaTelemetryDestinationWorkspace.builder()
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .telemetryDestinationId("telemetry_destination_id")
                        .workspaceId("workspace_id")
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(workspaceListPageResponse.data())
            .containsExactly(
                BetaTelemetryDestinationWorkspace.builder()
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .telemetryDestinationId("telemetry_destination_id")
                    .workspaceId("workspace_id")
                    .build()
            )
        assertThat(workspaceListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceListPageResponse =
            WorkspaceListPageResponse.builder()
                .addData(
                    BetaTelemetryDestinationWorkspace.builder()
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .telemetryDestinationId("telemetry_destination_id")
                        .workspaceId("workspace_id")
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedWorkspaceListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceListPageResponse),
                jacksonTypeRef<WorkspaceListPageResponse>(),
            )

        assertThat(roundtrippedWorkspaceListPageResponse).isEqualTo(workspaceListPageResponse)
    }
}
