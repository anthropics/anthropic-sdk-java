package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsBillingErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsBillingError =
            BetaManagedAgentsBillingError.builder()
                .message("message")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        assertThat(betaManagedAgentsBillingError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsBillingError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsBillingError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsBillingError =
            BetaManagedAgentsBillingError.builder()
                .message("message")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        val roundtrippedBetaManagedAgentsBillingError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsBillingError),
                jacksonTypeRef<BetaManagedAgentsBillingError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsBillingError)
            .isEqualTo(betaManagedAgentsBillingError)
    }
}
