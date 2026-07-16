// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTunnelTokenTest {

    @Test
    fun create() {
        val betaTunnelToken = BetaTunnelToken.builder().id("id").tunnelToken("tunnel_token").build()

        assertThat(betaTunnelToken.id()).isEqualTo("id")
        assertThat(betaTunnelToken.tunnelToken()).isEqualTo("tunnel_token")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTunnelToken = BetaTunnelToken.builder().id("id").tunnelToken("tunnel_token").build()

        val roundtrippedBetaTunnelToken =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTunnelToken),
                jacksonTypeRef<BetaTunnelToken>(),
            )

        assertThat(roundtrippedBetaTunnelToken).isEqualTo(betaTunnelToken)
    }
}
