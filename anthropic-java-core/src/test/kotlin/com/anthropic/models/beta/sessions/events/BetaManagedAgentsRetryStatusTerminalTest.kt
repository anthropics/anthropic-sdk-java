// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsRetryStatusTerminalTest {

    @Test
    fun create() {
        val betaManagedAgentsRetryStatusTerminal =
            BetaManagedAgentsRetryStatusTerminal.of(
                BetaManagedAgentsRetryStatusTerminal.Type.TERMINAL
            )

        assertThat(betaManagedAgentsRetryStatusTerminal.type())
            .isEqualTo(BetaManagedAgentsRetryStatusTerminal.Type.TERMINAL)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsRetryStatusTerminal =
            BetaManagedAgentsRetryStatusTerminal.of(
                BetaManagedAgentsRetryStatusTerminal.Type.TERMINAL
            )

        val roundtrippedBetaManagedAgentsRetryStatusTerminal =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsRetryStatusTerminal),
                jacksonTypeRef<BetaManagedAgentsRetryStatusTerminal>(),
            )

        assertThat(roundtrippedBetaManagedAgentsRetryStatusTerminal)
            .isEqualTo(betaManagedAgentsRetryStatusTerminal)
    }
}
