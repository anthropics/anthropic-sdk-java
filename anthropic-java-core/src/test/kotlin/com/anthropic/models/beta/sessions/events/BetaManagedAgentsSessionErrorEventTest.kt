// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionErrorEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionErrorEvent =
            BetaManagedAgentsSessionErrorEvent.builder()
                .id("id")
                .error(
                    BetaManagedAgentsUnknownError.builder()
                        .message("message")
                        .retryStatus(
                            BetaManagedAgentsRetryStatusRetrying.builder()
                                .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                                .build()
                        )
                        .type(BetaManagedAgentsUnknownError.Type.UNKNOWN_ERROR)
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionErrorEvent.Type.SESSION_ERROR)
                .build()

        assertThat(betaManagedAgentsSessionErrorEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSessionErrorEvent.error())
            .isEqualTo(
                BetaManagedAgentsSessionErrorEvent.Error.ofUnknown(
                    BetaManagedAgentsUnknownError.builder()
                        .message("message")
                        .retryStatus(
                            BetaManagedAgentsRetryStatusRetrying.builder()
                                .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                                .build()
                        )
                        .type(BetaManagedAgentsUnknownError.Type.UNKNOWN_ERROR)
                        .build()
                )
            )
        assertThat(betaManagedAgentsSessionErrorEvent.processedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsSessionErrorEvent.type())
            .isEqualTo(BetaManagedAgentsSessionErrorEvent.Type.SESSION_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionErrorEvent =
            BetaManagedAgentsSessionErrorEvent.builder()
                .id("id")
                .error(
                    BetaManagedAgentsUnknownError.builder()
                        .message("message")
                        .retryStatus(
                            BetaManagedAgentsRetryStatusRetrying.builder()
                                .type(BetaManagedAgentsRetryStatusRetrying.Type.RETRYING)
                                .build()
                        )
                        .type(BetaManagedAgentsUnknownError.Type.UNKNOWN_ERROR)
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsSessionErrorEvent.Type.SESSION_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsSessionErrorEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionErrorEvent),
                jacksonTypeRef<BetaManagedAgentsSessionErrorEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionErrorEvent)
            .isEqualTo(betaManagedAgentsSessionErrorEvent)
    }
}
