// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSelfHostedWorkHeartbeatResponseTest {

    @Test
    fun create() {
        val betaSelfHostedWorkHeartbeatResponse =
            BetaSelfHostedWorkHeartbeatResponse.builder()
                .lastHeartbeat("last_heartbeat")
                .leaseExtended(true)
                .state(BetaSelfHostedWorkHeartbeatResponse.State.QUEUED)
                .ttlSeconds(0L)
                .build()

        assertThat(betaSelfHostedWorkHeartbeatResponse.lastHeartbeat()).isEqualTo("last_heartbeat")
        assertThat(betaSelfHostedWorkHeartbeatResponse.leaseExtended()).isEqualTo(true)
        assertThat(betaSelfHostedWorkHeartbeatResponse.state())
            .isEqualTo(BetaSelfHostedWorkHeartbeatResponse.State.QUEUED)
        assertThat(betaSelfHostedWorkHeartbeatResponse.ttlSeconds()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSelfHostedWorkHeartbeatResponse =
            BetaSelfHostedWorkHeartbeatResponse.builder()
                .lastHeartbeat("last_heartbeat")
                .leaseExtended(true)
                .state(BetaSelfHostedWorkHeartbeatResponse.State.QUEUED)
                .ttlSeconds(0L)
                .build()

        val roundtrippedBetaSelfHostedWorkHeartbeatResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSelfHostedWorkHeartbeatResponse),
                jacksonTypeRef<BetaSelfHostedWorkHeartbeatResponse>(),
            )

        assertThat(roundtrippedBetaSelfHostedWorkHeartbeatResponse)
            .isEqualTo(betaSelfHostedWorkHeartbeatResponse)
    }
}
