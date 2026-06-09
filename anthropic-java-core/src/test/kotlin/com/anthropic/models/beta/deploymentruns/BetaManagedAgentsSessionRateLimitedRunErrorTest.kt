// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionRateLimitedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionRateLimitedRunError =
            BetaManagedAgentsSessionRateLimitedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsSessionRateLimitedRunError.Type.SESSION_RATE_LIMITED_ERROR)
                .build()

        assertThat(betaManagedAgentsSessionRateLimitedRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsSessionRateLimitedRunError.type())
            .isEqualTo(BetaManagedAgentsSessionRateLimitedRunError.Type.SESSION_RATE_LIMITED_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionRateLimitedRunError =
            BetaManagedAgentsSessionRateLimitedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsSessionRateLimitedRunError.Type.SESSION_RATE_LIMITED_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsSessionRateLimitedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionRateLimitedRunError),
                jacksonTypeRef<BetaManagedAgentsSessionRateLimitedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionRateLimitedRunError)
            .isEqualTo(betaManagedAgentsSessionRateLimitedRunError)
    }
}
