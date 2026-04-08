// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsRetryStatusRetryingTest {

    @Test
    fun create() {
        val betaManagedAgentsRetryStatusRetrying =
            BetaManagedAgentsRetryStatusRetrying.builder()
                .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                .build()

        assertThat(betaManagedAgentsRetryStatusRetrying.type())
            .isEqualTo(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsRetryStatusRetrying =
            BetaManagedAgentsRetryStatusRetrying.builder()
                .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                .build()

        val roundtrippedBetaManagedAgentsRetryStatusRetrying =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsRetryStatusRetrying),
                jacksonTypeRef<BetaManagedAgentsRetryStatusRetrying>(),
            )

        assertThat(roundtrippedBetaManagedAgentsRetryStatusRetrying)
            .isEqualTo(betaManagedAgentsRetryStatusRetrying)
    }
}
