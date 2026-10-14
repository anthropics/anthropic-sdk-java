package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationWorkspacesExcludeParamsTest {

    @Test
    fun create() {
        val betaTelemetryDestinationWorkspacesExcludeParams =
            BetaTelemetryDestinationWorkspacesExcludeParams.builder()
                .addWorkspaceId("string")
                .build()

        assertThat(betaTelemetryDestinationWorkspacesExcludeParams.workspaceIds().getOrNull())
            .containsExactly("string")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaTelemetryDestinationWorkspacesExcludeParams =
            BetaTelemetryDestinationWorkspacesExcludeParams.builder().build()

        val betaTelemetryDestinationWorkspacesExcludeParams =
            baseBetaTelemetryDestinationWorkspacesExcludeParams
                .toBuilder()
                .addWorkspaceId("string")
                .build()

        assertThat(betaTelemetryDestinationWorkspacesExcludeParams.workspaceIds().getOrNull())
            .containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationWorkspacesExcludeParams =
            BetaTelemetryDestinationWorkspacesExcludeParams.builder()
                .addWorkspaceId("string")
                .build()

        val roundtrippedBetaTelemetryDestinationWorkspacesExcludeParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationWorkspacesExcludeParams),
                jacksonTypeRef<BetaTelemetryDestinationWorkspacesExcludeParams>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationWorkspacesExcludeParams)
            .isEqualTo(betaTelemetryDestinationWorkspacesExcludeParams)
    }
}
