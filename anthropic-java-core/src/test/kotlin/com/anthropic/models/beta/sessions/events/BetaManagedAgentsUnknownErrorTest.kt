package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUnknownErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsUnknownError =
            BetaManagedAgentsUnknownError.builder()
                .message("message")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        assertThat(betaManagedAgentsUnknownError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsUnknownError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsUnknownError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUnknownError =
            BetaManagedAgentsUnknownError.builder()
                .message("message")
                .retryStatus(BetaManagedAgentsRetryStatusRetrying.builder().build())
                .build()

        val roundtrippedBetaManagedAgentsUnknownError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUnknownError),
                jacksonTypeRef<BetaManagedAgentsUnknownError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUnknownError)
            .isEqualTo(betaManagedAgentsUnknownError)
    }
}
