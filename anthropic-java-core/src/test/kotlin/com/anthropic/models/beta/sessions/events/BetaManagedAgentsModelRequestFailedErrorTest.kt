// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsModelRequestFailedErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsModelRequestFailedError =
            BetaManagedAgentsModelRequestFailedError.builder()
                .message("message")
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
                .type(BetaManagedAgentsModelRequestFailedError.Type.MODEL_REQUEST_FAILED_ERROR)
                .build()

        assertThat(betaManagedAgentsModelRequestFailedError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsModelRequestFailedError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsModelRequestFailedError.RetryStatus.ofRetrying(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
            )
        assertThat(betaManagedAgentsModelRequestFailedError.type())
            .isEqualTo(BetaManagedAgentsModelRequestFailedError.Type.MODEL_REQUEST_FAILED_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsModelRequestFailedError =
            BetaManagedAgentsModelRequestFailedError.builder()
                .message("message")
                .retryStatus(
                    BetaManagedAgentsRetryStatusRetrying.builder()
                        .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                        .build()
                )
                .type(BetaManagedAgentsModelRequestFailedError.Type.MODEL_REQUEST_FAILED_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsModelRequestFailedError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsModelRequestFailedError),
                jacksonTypeRef<BetaManagedAgentsModelRequestFailedError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsModelRequestFailedError)
            .isEqualTo(betaManagedAgentsModelRequestFailedError)
    }
}
