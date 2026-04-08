// File generated from our OpenAPI spec by Stainless.

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
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
                .type(BetaManagedAgentsUnknownError.Type.UNKNOWN_ERROR)
                .build()

        assertThat(betaManagedAgentsUnknownError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsUnknownError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsUnknownError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
            )
        assertThat(betaManagedAgentsUnknownError.type())
            .isEqualTo(BetaManagedAgentsUnknownError.Type.UNKNOWN_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUnknownError =
            BetaManagedAgentsUnknownError.builder()
                .message("message")
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
                .type(BetaManagedAgentsUnknownError.Type.UNKNOWN_ERROR)
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
