package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsModelOverloadedErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsModelOverloadedError =
            BetaManagedAgentsModelOverloadedError.builder()
                .message("message")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        assertThat(betaManagedAgentsModelOverloadedError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsModelOverloadedError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsModelOverloadedError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsModelOverloadedError =
            BetaManagedAgentsModelOverloadedError.builder()
                .message("message")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        val roundtrippedBetaManagedAgentsModelOverloadedError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsModelOverloadedError),
                jacksonTypeRef<BetaManagedAgentsModelOverloadedError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsModelOverloadedError)
            .isEqualTo(betaManagedAgentsModelOverloadedError)
    }
}
