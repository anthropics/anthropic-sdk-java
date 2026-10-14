package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
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
internal class TelemetryDestinationServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val telemetryDestinationServiceAsync = client.beta().organization().telemetryDestinations()

        val betaTelemetryDestinationFuture =
            telemetryDestinationServiceAsync.create(
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

        val betaTelemetryDestination = betaTelemetryDestinationFuture.get()
        betaTelemetryDestination.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val telemetryDestinationServiceAsync = client.beta().organization().telemetryDestinations()

        val betaTelemetryDestinationFuture =
            telemetryDestinationServiceAsync.retrieve(
                TelemetryDestinationRetrieveParams.builder()
                    .destinationId("destination_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaTelemetryDestination = betaTelemetryDestinationFuture.get()
        betaTelemetryDestination.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val telemetryDestinationServiceAsync = client.beta().organization().telemetryDestinations()

        val betaTelemetryDestinationFuture =
            telemetryDestinationServiceAsync.update(
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

        val betaTelemetryDestination = betaTelemetryDestinationFuture.get()
        betaTelemetryDestination.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val telemetryDestinationServiceAsync = client.beta().organization().telemetryDestinations()

        val pageFuture = telemetryDestinationServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val telemetryDestinationServiceAsync = client.beta().organization().telemetryDestinations()

        val betaDeletedTelemetryDestinationFuture =
            telemetryDestinationServiceAsync.delete(
                TelemetryDestinationDeleteParams.builder()
                    .destinationId("destination_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaDeletedTelemetryDestination = betaDeletedTelemetryDestinationFuture.get()
        betaDeletedTelemetryDestination.validate()
    }

    @Test
    fun test() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val telemetryDestinationServiceAsync = client.beta().organization().telemetryDestinations()

        val betaTelemetryDestinationTestFuture =
            telemetryDestinationServiceAsync.test(
                TelemetryDestinationTestParams.builder()
                    .destinationId("destination_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaTelemetryDestinationTest = betaTelemetryDestinationTestFuture.get()
        betaTelemetryDestinationTest.validate()
    }
}
