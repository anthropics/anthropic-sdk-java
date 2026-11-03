package com.anthropic.models.beta.environments.work

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWorkLeaseStateTest {

    @Test
    fun create() {
        val betaWorkLeaseState =
            BetaWorkLeaseState.builder()
                .lastHeartbeat("last_heartbeat")
                .leaseExtended(true)
                .state(BetaWorkLeaseState.State.QUEUED)
                .ttlSeconds(0L)
                .build()

        assertThat(betaWorkLeaseState.lastHeartbeat()).contains("last_heartbeat")
        assertThat(betaWorkLeaseState.leaseExtended()).isEqualTo(true)
        assertThat(betaWorkLeaseState.state()).isEqualTo(BetaWorkLeaseState.State.QUEUED)
        assertThat(betaWorkLeaseState.ttlSeconds()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWorkLeaseState =
            BetaWorkLeaseState.builder()
                .lastHeartbeat("last_heartbeat")
                .leaseExtended(true)
                .state(BetaWorkLeaseState.State.QUEUED)
                .ttlSeconds(0L)
                .build()

        val roundtrippedBetaWorkLeaseState =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWorkLeaseState),
                jacksonTypeRef<BetaWorkLeaseState>(),
            )

        assertThat(roundtrippedBetaWorkLeaseState).isEqualTo(betaWorkLeaseState)
    }
}
