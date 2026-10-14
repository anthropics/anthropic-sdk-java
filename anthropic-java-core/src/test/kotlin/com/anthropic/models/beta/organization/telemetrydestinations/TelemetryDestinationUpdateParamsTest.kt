package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TelemetryDestinationUpdateParamsTest {

    @Test
    fun create() {
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
    }

    @Test
    fun pathParams() {
        val params =
            TelemetryDestinationUpdateParams.builder().destinationId("destination_id").build()

        assertThat(params._pathParam(0)).isEqualTo("destination_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
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

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("message-batches-2024-09-24", "telemetry-destinations-2026-08-11")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            TelemetryDestinationUpdateParams.builder().destinationId("destination_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
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

        val body = params._body()

        assertThat(body.displayName()).contains("display_name")
        assertThat(body.endpointUrl()).contains("endpoint_url")
        assertThat(body.headers())
            .contains(
                TelemetryDestinationUpdateParams.DestinationHeaders.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(body.state())
            .contains(
                BetaTelemetryDestinationStateParams.ofEnabled(
                    BetaTelemetryDestinationStateEnabledParams.builder().build()
                )
            )
        assertThat(body.traces())
            .contains(
                BetaTelemetryDestinationTracesParams.builder()
                    .contentMode(BetaTelemetryDestinationContentMode.METADATA_ONLY)
                    .enabled(true)
                    .endpointUrl("endpoint_url")
                    .maxContentBytes(0)
                    .build()
            )
        assertThat(body.workspaces())
            .contains(
                BetaTelemetryDestinationWorkspacesParams.ofAll(
                    BetaTelemetryDestinationWorkspacesAllParams.builder().build()
                )
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            TelemetryDestinationUpdateParams.builder().destinationId("destination_id").build()

        val body = params._body()
    }
}
