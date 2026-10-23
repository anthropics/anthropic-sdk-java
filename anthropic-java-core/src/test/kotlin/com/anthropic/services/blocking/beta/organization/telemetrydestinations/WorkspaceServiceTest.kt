package com.anthropic.services.blocking.beta.organization.telemetrydestinations

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.telemetrydestinations.workspaces.WorkspaceRemoveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WorkspaceServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val workspaceService =
            client.beta().organization().telemetryDestinations().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = workspaceService.list("destination_id")

        page.response().validate()
    }

    @Test
    fun add() {
        val workspaceService =
            client.beta().organization().telemetryDestinations().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaTelemetryDestinationWorkspace =
            workspaceService.add(
                WorkspaceAddParams.builder()
                    .destinationId("destination_id")
                    .workspaceId("workspace_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaTelemetryDestinationWorkspace.validate()
    }

    @Test
    fun remove() {
        val workspaceService =
            client.beta().organization().telemetryDestinations().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaDeletedTelemetryDestinationWorkspace =
            workspaceService.remove(
                WorkspaceRemoveParams.builder()
                    .destinationId("destination_id")
                    .workspaceId("workspace_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaDeletedTelemetryDestinationWorkspace.validate()
    }
}
