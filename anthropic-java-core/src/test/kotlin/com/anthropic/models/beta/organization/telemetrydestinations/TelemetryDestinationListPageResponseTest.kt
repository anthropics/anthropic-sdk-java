package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TelemetryDestinationListPageResponseTest {

    @Test
    fun create() {
        val telemetryDestinationListPageResponse =
            TelemetryDestinationListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(telemetryDestinationListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(telemetryDestinationListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val telemetryDestinationListPageResponse =
            TelemetryDestinationListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedTelemetryDestinationListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(telemetryDestinationListPageResponse),
                jacksonTypeRef<TelemetryDestinationListPageResponse>(),
            )

        assertThat(roundtrippedTelemetryDestinationListPageResponse)
            .isEqualTo(telemetryDestinationListPageResponse)
    }
}
