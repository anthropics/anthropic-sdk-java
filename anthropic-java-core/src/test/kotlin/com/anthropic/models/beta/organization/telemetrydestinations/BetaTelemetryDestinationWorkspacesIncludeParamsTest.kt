package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationWorkspacesIncludeParamsTest {

    @Test
    fun create() {
        val betaTelemetryDestinationWorkspacesIncludeParams =
            BetaTelemetryDestinationWorkspacesIncludeParams.builder()
                .addWorkspaceId("string")
                .build()

        assertThat(betaTelemetryDestinationWorkspacesIncludeParams.workspaceIds().getOrNull())
            .containsExactly("string")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaTelemetryDestinationWorkspacesIncludeParams =
            BetaTelemetryDestinationWorkspacesIncludeParams.builder().build()

        val betaTelemetryDestinationWorkspacesIncludeParams =
            baseBetaTelemetryDestinationWorkspacesIncludeParams
                .toBuilder()
                .addWorkspaceId("string")
                .build()

        assertThat(betaTelemetryDestinationWorkspacesIncludeParams.workspaceIds().getOrNull())
            .containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestinationWorkspacesIncludeParams =
            BetaTelemetryDestinationWorkspacesIncludeParams.builder()
                .addWorkspaceId("string")
                .build()

        val roundtrippedBetaTelemetryDestinationWorkspacesIncludeParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestinationWorkspacesIncludeParams),
                jacksonTypeRef<BetaTelemetryDestinationWorkspacesIncludeParams>(),
            )

        assertThat(roundtrippedBetaTelemetryDestinationWorkspacesIncludeParams)
            .isEqualTo(betaTelemetryDestinationWorkspacesIncludeParams)
    }
}
