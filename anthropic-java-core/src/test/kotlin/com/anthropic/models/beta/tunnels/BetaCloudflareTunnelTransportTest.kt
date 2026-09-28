package com.anthropic.models.beta.tunnels

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCloudflareTunnelTransportTest {

    @Test
    fun create() {
        val betaCloudflareTunnelTransport = BetaCloudflareTunnelTransport.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCloudflareTunnelTransport = BetaCloudflareTunnelTransport.builder().build()

        val roundtrippedBetaCloudflareTunnelTransport =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCloudflareTunnelTransport),
                jacksonTypeRef<BetaCloudflareTunnelTransport>(),
            )

        assertThat(roundtrippedBetaCloudflareTunnelTransport)
            .isEqualTo(betaCloudflareTunnelTransport)
    }
}
