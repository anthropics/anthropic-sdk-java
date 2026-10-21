package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionRateLimitedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionRateLimitedRunError =
            BetaManagedAgentsSessionRateLimitedRunError.of("message")

        assertThat(betaManagedAgentsSessionRateLimitedRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionRateLimitedRunError =
            BetaManagedAgentsSessionRateLimitedRunError.of("message")

        val roundtrippedBetaManagedAgentsSessionRateLimitedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionRateLimitedRunError),
                jacksonTypeRef<BetaManagedAgentsSessionRateLimitedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionRateLimitedRunError)
            .isEqualTo(betaManagedAgentsSessionRateLimitedRunError)
    }
}
