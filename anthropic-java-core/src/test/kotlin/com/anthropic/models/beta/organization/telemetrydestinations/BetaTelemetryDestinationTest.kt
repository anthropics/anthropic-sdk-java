package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTelemetryDestinationTest {

    @Test
    fun create() {
        val betaTelemetryDestination =
            BetaTelemetryDestination.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .deliveryHealth(
                    BetaTelemetryDestinationDeliveryHealth.builder()
                        .lastError("last_error")
                        .lastErrorAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .lastSuccessAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .displayName("display_name")
                .endpointUrl("endpoint_url")
                .addHeaderName("string")
                .state(BetaTelemetryDestinationStateEnabled.builder().build())
                .traces(
                    BetaTelemetryDestinationTraces.builder()
                        .contentMode(BetaTelemetryDestinationContentMode.METADATA_ONLY)
                        .enabled(true)
                        .endpointUrl("endpoint_url")
                        .maxContentBytes(0)
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .workspaces(BetaTelemetryDestinationWorkspacesAll.builder().build())
                .build()

        assertThat(betaTelemetryDestination.id()).isEqualTo("id")
        assertThat(betaTelemetryDestination.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaTelemetryDestination.deliveryHealth())
            .isEqualTo(
                BetaTelemetryDestinationDeliveryHealth.builder()
                    .lastError("last_error")
                    .lastErrorAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .lastSuccessAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )
        assertThat(betaTelemetryDestination.displayName()).contains("display_name")
        assertThat(betaTelemetryDestination.endpointUrl()).isEqualTo("endpoint_url")
        assertThat(betaTelemetryDestination.headerNames()).containsExactly("string")
        assertThat(betaTelemetryDestination.state())
            .isEqualTo(
                BetaTelemetryDestinationState.ofEnabled(
                    BetaTelemetryDestinationStateEnabled.builder().build()
                )
            )
        assertThat(betaTelemetryDestination.traces())
            .isEqualTo(
                BetaTelemetryDestinationTraces.builder()
                    .contentMode(BetaTelemetryDestinationContentMode.METADATA_ONLY)
                    .enabled(true)
                    .endpointUrl("endpoint_url")
                    .maxContentBytes(0)
                    .build()
            )
        assertThat(betaTelemetryDestination.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaTelemetryDestination.workspaces())
            .isEqualTo(
                BetaTelemetryDestinationWorkspaces.ofAll(
                    BetaTelemetryDestinationWorkspacesAll.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTelemetryDestination =
            BetaTelemetryDestination.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .deliveryHealth(
                    BetaTelemetryDestinationDeliveryHealth.builder()
                        .lastError("last_error")
                        .lastErrorAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .lastSuccessAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .displayName("display_name")
                .endpointUrl("endpoint_url")
                .addHeaderName("string")
                .state(BetaTelemetryDestinationStateEnabled.builder().build())
                .traces(
                    BetaTelemetryDestinationTraces.builder()
                        .contentMode(BetaTelemetryDestinationContentMode.METADATA_ONLY)
                        .enabled(true)
                        .endpointUrl("endpoint_url")
                        .maxContentBytes(0)
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .workspaces(BetaTelemetryDestinationWorkspacesAll.builder().build())
                .build()

        val roundtrippedBetaTelemetryDestination =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTelemetryDestination),
                jacksonTypeRef<BetaTelemetryDestination>(),
            )

        assertThat(roundtrippedBetaTelemetryDestination).isEqualTo(betaTelemetryDestination)
    }
}
