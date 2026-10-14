package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TelemetryDestinationCreateParamsTest {

    @Test
    fun create() {
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
    }

    @Test
    fun headers() {
        val params =
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
            TelemetryDestinationCreateParams.builder()
                .endpointUrl("x")
                .workspaces(BetaTelemetryDestinationWorkspacesAllParams.builder().build())
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
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

        val body = params._body()

        assertThat(body.endpointUrl()).isEqualTo("x")
        assertThat(body.workspaces())
            .isEqualTo(
                BetaTelemetryDestinationWorkspacesParams.ofAll(
                    BetaTelemetryDestinationWorkspacesAllParams.builder().build()
                )
            )
        assertThat(body.displayName()).contains("display_name")
        assertThat(body.headers())
            .contains(
                TelemetryDestinationCreateParams.DestinationHeaders.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
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
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            TelemetryDestinationCreateParams.builder()
                .endpointUrl("x")
                .workspaces(BetaTelemetryDestinationWorkspacesAllParams.builder().build())
                .build()

        val body = params._body()

        assertThat(body.endpointUrl()).isEqualTo("x")
        assertThat(body.workspaces())
            .isEqualTo(
                BetaTelemetryDestinationWorkspacesParams.ofAll(
                    BetaTelemetryDestinationWorkspacesAllParams.builder().build()
                )
            )
    }
}
