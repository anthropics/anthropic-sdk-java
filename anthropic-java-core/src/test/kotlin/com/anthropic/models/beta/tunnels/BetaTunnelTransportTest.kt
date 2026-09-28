package com.anthropic.models.beta.tunnels

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaTunnelTransportTest {

    @Test
    fun ofCloudflare() {
        val cloudflare = BetaCloudflareTunnelTransport.builder().build()

        val betaTunnelTransport = BetaTunnelTransport.ofCloudflare(cloudflare)

        assertThat(betaTunnelTransport.cloudflare()).contains(cloudflare)
        assertThat(betaTunnelTransport.relay()).isEmpty
    }

    @Test
    fun ofCloudflareRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTunnelTransport =
            BetaTunnelTransport.ofCloudflare(BetaCloudflareTunnelTransport.builder().build())

        val roundtrippedBetaTunnelTransport =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTunnelTransport),
                jacksonTypeRef<BetaTunnelTransport>(),
            )

        assertThat(roundtrippedBetaTunnelTransport).isEqualTo(betaTunnelTransport)
    }

    @Test
    fun ofRelay() {
        val relay =
            BetaRelayTunnelTransport.builder()
                .token(BetaTunnelToken.builder().id("id").tunnelToken("tunnel_token").build())
                .build()

        val betaTunnelTransport = BetaTunnelTransport.ofRelay(relay)

        assertThat(betaTunnelTransport.cloudflare()).isEmpty
        assertThat(betaTunnelTransport.relay()).contains(relay)
    }

    @Test
    fun ofRelayRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTunnelTransport =
            BetaTunnelTransport.ofRelay(
                BetaRelayTunnelTransport.builder()
                    .token(BetaTunnelToken.builder().id("id").tunnelToken("tunnel_token").build())
                    .build()
            )

        val roundtrippedBetaTunnelTransport =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTunnelTransport),
                jacksonTypeRef<BetaTunnelTransport>(),
            )

        assertThat(roundtrippedBetaTunnelTransport).isEqualTo(betaTunnelTransport)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaTunnelTransport =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaTunnelTransport>())

        val e = assertThrows<AnthropicInvalidDataException> { betaTunnelTransport.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
