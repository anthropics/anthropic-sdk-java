package com.anthropic.models.beta.environments.work

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaHeartbeatPreconditionFailedErrorDetailsTest {

    @Test
    fun create() {
        val betaHeartbeatPreconditionFailedErrorDetails =
            BetaHeartbeatPreconditionFailedErrorDetails.of(
                BetaWorkLeaseState.builder()
                    .lastHeartbeat("last_heartbeat")
                    .leaseExtended(true)
                    .state(BetaWorkLeaseState.State.QUEUED)
                    .ttlSeconds(0L)
                    .build()
            )

        assertThat(betaHeartbeatPreconditionFailedErrorDetails.currentState())
            .isEqualTo(
                BetaWorkLeaseState.builder()
                    .lastHeartbeat("last_heartbeat")
                    .leaseExtended(true)
                    .state(BetaWorkLeaseState.State.QUEUED)
                    .ttlSeconds(0L)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaHeartbeatPreconditionFailedErrorDetails =
            BetaHeartbeatPreconditionFailedErrorDetails.of(
                BetaWorkLeaseState.builder()
                    .lastHeartbeat("last_heartbeat")
                    .leaseExtended(true)
                    .state(BetaWorkLeaseState.State.QUEUED)
                    .ttlSeconds(0L)
                    .build()
            )

        val roundtrippedBetaHeartbeatPreconditionFailedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaHeartbeatPreconditionFailedErrorDetails),
                jacksonTypeRef<BetaHeartbeatPreconditionFailedErrorDetails>(),
            )

        assertThat(roundtrippedBetaHeartbeatPreconditionFailedErrorDetails)
            .isEqualTo(betaHeartbeatPreconditionFailedErrorDetails)
    }
}
