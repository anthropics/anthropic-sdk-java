// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsRetryStatusExhaustedTest {

    @Test
    fun create() {
        val betaManagedAgentsRetryStatusExhausted =
            BetaManagedAgentsRetryStatusExhausted.builder()
                .type(BetaManagedAgentsRetryStatusExhausted.Type.EXHAUSTED)
                .build()

        assertThat(betaManagedAgentsRetryStatusExhausted.type())
            .isEqualTo(BetaManagedAgentsRetryStatusExhausted.Type.EXHAUSTED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsRetryStatusExhausted =
            BetaManagedAgentsRetryStatusExhausted.builder()
                .type(BetaManagedAgentsRetryStatusExhausted.Type.EXHAUSTED)
                .build()

        val roundtrippedBetaManagedAgentsRetryStatusExhausted =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsRetryStatusExhausted),
                jacksonTypeRef<BetaManagedAgentsRetryStatusExhausted>(),
            )

        assertThat(roundtrippedBetaManagedAgentsRetryStatusExhausted)
            .isEqualTo(betaManagedAgentsRetryStatusExhausted)
    }
}
