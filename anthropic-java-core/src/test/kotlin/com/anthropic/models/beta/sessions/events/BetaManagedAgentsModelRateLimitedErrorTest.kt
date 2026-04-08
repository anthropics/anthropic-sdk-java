// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsModelRateLimitedErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsModelRateLimitedError =
            BetaManagedAgentsModelRateLimitedError.builder()
                .message("message")
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
                .type(BetaManagedAgentsModelRateLimitedError.Type.MODEL_RATE_LIMITED_ERROR)
                .build()

        assertThat(betaManagedAgentsModelRateLimitedError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsModelRateLimitedError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsModelRateLimitedError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
            )
        assertThat(betaManagedAgentsModelRateLimitedError.type())
            .isEqualTo(BetaManagedAgentsModelRateLimitedError.Type.MODEL_RATE_LIMITED_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsModelRateLimitedError =
            BetaManagedAgentsModelRateLimitedError.builder()
                .message("message")
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
                .type(BetaManagedAgentsModelRateLimitedError.Type.MODEL_RATE_LIMITED_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsModelRateLimitedError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsModelRateLimitedError),
                jacksonTypeRef<BetaManagedAgentsModelRateLimitedError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsModelRateLimitedError)
            .isEqualTo(betaManagedAgentsModelRateLimitedError)
    }
}
