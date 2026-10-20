package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.telemetrydestinations.BetaTelemetryDestinationContentMode
import com.anthropic.models.beta.organization.telemetrydestinations.BetaTelemetryDestinationStateEnabledParams
import com.anthropic.models.beta.organization.telemetrydestinations.BetaTelemetryDestinationTracesParams
import com.anthropic.models.beta.organization.telemetrydestinations.BetaTelemetryDestinationWorkspacesAllParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationCreateParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationDeleteParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationRetrieveParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationTestParams
import com.anthropic.models.beta.organization.telemetrydestinations.TelemetryDestinationUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class TelemetryDestinationServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val telemetryDestinationService = client.beta().organization().telemetryDestinations()

        val betaTelemetryDestination =
            telemetryDestinationService.create(
                TelemetryDestinationCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .endpointUrl("x")
                    .workspaces(BetaTelemetryDestinationWorkspacesAllParams.builder().build())
                    .displayName("display_name")
                    .headers(
                        TelemetryDestinationCreateParams.DestinationHeaders.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .traces(
                        BetaTelemetryDestinationTracesParams.builder()
                            .contentMode(BetaTelemetryDestinationContentMode.METADATA_ONLY)
                            .enabled(true)
                            .endpointUrl("endpoint_url")
                            .maxContentBytes(0)
                            .build()
                    )
                    .build()
            )

        betaTelemetryDestination.validate()
    }

    @Test
    fun retrieve() {
        val telemetryDestinationService = client.beta().organization().telemetryDestinations()

        val betaTelemetryDestination =
            telemetryDestinationService.retrieve(
                TelemetryDestinationRetrieveParams.builder()
                    .destinationId("destination_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaTelemetryDestination.validate()
    }

    @Test
    fun update() {
        val telemetryDestinationService = client.beta().organization().telemetryDestinations()

        val betaTelemetryDestination =
            telemetryDestinationService.update(
                TelemetryDestinationUpdateParams.builder()
                    .destinationId("destination_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .displayName("display_name")
                    .endpointUrl("endpoint_url")
                    .headers(
                        TelemetryDestinationUpdateParams.DestinationHeaders.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .state(BetaTelemetryDestinationStateEnabledParams.builder().build())
                    .traces(
                        BetaTelemetryDestinationTracesParams.builder()
                            .contentMode(BetaTelemetryDestinationContentMode.METADATA_ONLY)
                            .enabled(true)
                            .endpointUrl("endpoint_url")
                            .maxContentBytes(0)
                            .build()
                    )
                    .workspaces(BetaTelemetryDestinationWorkspacesAllParams.builder().build())
                    .build()
            )

        betaTelemetryDestination.validate()
    }

    @Test
    fun list() {
        val telemetryDestinationService = client.beta().organization().telemetryDestinations()

        val page = telemetryDestinationService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val telemetryDestinationService = client.beta().organization().telemetryDestinations()

        val betaDeletedTelemetryDestination =
            telemetryDestinationService.delete(
                TelemetryDestinationDeleteParams.builder()
                    .destinationId("destination_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaDeletedTelemetryDestination.validate()
    }

    @Test
    fun test() {
        val telemetryDestinationService = client.beta().organization().telemetryDestinations()

        val betaTelemetryDestinationTestResponse =
            telemetryDestinationService.test(
                TelemetryDestinationTestParams.builder()
                    .destinationId("destination_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaTelemetryDestinationTestResponse.validate()
    }
}
