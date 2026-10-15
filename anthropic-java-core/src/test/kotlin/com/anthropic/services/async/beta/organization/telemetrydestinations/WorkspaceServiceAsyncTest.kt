package com.anthropic.services.async.beta.organization.telemetrydestinations

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceRemoveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WorkspaceServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val workspaceServiceAsync =
            client.beta().organization().telemetryDestinations().workspaces()

        val pageFuture = workspaceServiceAsync.list("destination_id")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun add() {
        val workspaceServiceAsync =
            client.beta().organization().telemetryDestinations().workspaces()

        val betaTelemetryDestinationWorkspaceFuture =
            workspaceServiceAsync.add(
                WorkspaceAddParams.builder()
                    .destinationId("destination_id")
                    .workspaceId("workspace_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaTelemetryDestinationWorkspace = betaTelemetryDestinationWorkspaceFuture.get()
        betaTelemetryDestinationWorkspace.validate()
    }

    @Test
    fun remove() {
        val workspaceServiceAsync =
            client.beta().organization().telemetryDestinations().workspaces()

        val betaDeletedTelemetryDestinationWorkspaceFuture =
            workspaceServiceAsync.remove(
                WorkspaceRemoveParams.builder()
                    .destinationId("destination_id")
                    .workspaceId("workspace_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaDeletedTelemetryDestinationWorkspace =
            betaDeletedTelemetryDestinationWorkspaceFuture.get()
        betaDeletedTelemetryDestinationWorkspace.validate()
    }
}
