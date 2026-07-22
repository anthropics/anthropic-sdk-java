// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSelfHostedWorkListResponseTest {

    @Test
    fun create() {
        val betaSelfHostedWorkListResponse =
            BetaSelfHostedWorkListResponse.builder()
                .addData(
                    BetaSelfHostedWork.builder()
                        .id("id")
                        .acknowledgedAt("acknowledged_at")
                        .createdAt("created_at")
                        .data(BetaSessionWorkData.builder().id("id").build())
                        .environmentId("environment_id")
                        .latestHeartbeatAt("latest_heartbeat_at")
                        .metadata(
                            BetaSelfHostedWork.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .secret("secret")
                        .startedAt("started_at")
                        .state(BetaSelfHostedWork.State.QUEUED)
                        .stopRequestedAt("stop_requested_at")
                        .stoppedAt("stopped_at")
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(betaSelfHostedWorkListResponse.data())
            .containsExactly(
                BetaSelfHostedWork.builder()
                    .id("id")
                    .acknowledgedAt("acknowledged_at")
                    .createdAt("created_at")
                    .data(BetaSessionWorkData.builder().id("id").build())
                    .environmentId("environment_id")
                    .latestHeartbeatAt("latest_heartbeat_at")
                    .metadata(
                        BetaSelfHostedWork.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .secret("secret")
                    .startedAt("started_at")
                    .state(BetaSelfHostedWork.State.QUEUED)
                    .stopRequestedAt("stop_requested_at")
                    .stoppedAt("stopped_at")
                    .build()
            )
        assertThat(betaSelfHostedWorkListResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSelfHostedWorkListResponse =
            BetaSelfHostedWorkListResponse.builder()
                .addData(
                    BetaSelfHostedWork.builder()
                        .id("id")
                        .acknowledgedAt("acknowledged_at")
                        .createdAt("created_at")
                        .data(BetaSessionWorkData.builder().id("id").build())
                        .environmentId("environment_id")
                        .latestHeartbeatAt("latest_heartbeat_at")
                        .metadata(
                            BetaSelfHostedWork.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .secret("secret")
                        .startedAt("started_at")
                        .state(BetaSelfHostedWork.State.QUEUED)
                        .stopRequestedAt("stop_requested_at")
                        .stoppedAt("stopped_at")
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedBetaSelfHostedWorkListResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSelfHostedWorkListResponse),
                jacksonTypeRef<BetaSelfHostedWorkListResponse>(),
            )

        assertThat(roundtrippedBetaSelfHostedWorkListResponse)
            .isEqualTo(betaSelfHostedWorkListResponse)
    }
}
