package com.anthropic.models.beta.tunnels

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRelayTunnelTransportTest {

    @Test
    fun create() {
        val betaRelayTunnelTransport =
            BetaRelayTunnelTransport.builder()
                .token(BetaTunnelToken.builder().id("id").tunnelToken("tunnel_token").build())
                .build()

        assertThat(betaRelayTunnelTransport.token())
            .contains(BetaTunnelToken.builder().id("id").tunnelToken("tunnel_token").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRelayTunnelTransport =
            BetaRelayTunnelTransport.builder()
                .token(BetaTunnelToken.builder().id("id").tunnelToken("tunnel_token").build())
                .build()

        val roundtrippedBetaRelayTunnelTransport =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRelayTunnelTransport),
                jacksonTypeRef<BetaRelayTunnelTransport>(),
            )

        assertThat(roundtrippedBetaRelayTunnelTransport).isEqualTo(betaRelayTunnelTransport)
    }
}
