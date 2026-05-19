// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSelfHostedWorkTest {

    @Test
    fun create() {
        val betaSelfHostedWork =
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
                .startedAt("started_at")
                .state(BetaSelfHostedWork.State.QUEUED)
                .stopRequestedAt("stop_requested_at")
                .stoppedAt("stopped_at")
                .build()

        assertThat(betaSelfHostedWork.id()).isEqualTo("id")
        assertThat(betaSelfHostedWork.acknowledgedAt()).contains("acknowledged_at")
        assertThat(betaSelfHostedWork.createdAt()).isEqualTo("created_at")
        assertThat(betaSelfHostedWork.data())
            .isEqualTo(BetaSessionWorkData.builder().id("id").build())
        assertThat(betaSelfHostedWork.environmentId()).isEqualTo("environment_id")
        assertThat(betaSelfHostedWork.latestHeartbeatAt()).contains("latest_heartbeat_at")
        assertThat(betaSelfHostedWork.metadata())
            .isEqualTo(
                BetaSelfHostedWork.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(betaSelfHostedWork.startedAt()).contains("started_at")
        assertThat(betaSelfHostedWork.state()).isEqualTo(BetaSelfHostedWork.State.QUEUED)
        assertThat(betaSelfHostedWork.stopRequestedAt()).contains("stop_requested_at")
        assertThat(betaSelfHostedWork.stoppedAt()).contains("stopped_at")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSelfHostedWork =
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
                .startedAt("started_at")
                .state(BetaSelfHostedWork.State.QUEUED)
                .stopRequestedAt("stop_requested_at")
                .stoppedAt("stopped_at")
                .build()

        val roundtrippedBetaSelfHostedWork =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSelfHostedWork),
                jacksonTypeRef<BetaSelfHostedWork>(),
            )

        assertThat(roundtrippedBetaSelfHostedWork).isEqualTo(betaSelfHostedWork)
    }
}
