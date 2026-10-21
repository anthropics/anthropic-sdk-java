package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEnvironmentNotFoundRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsEnvironmentNotFoundRunError =
            BetaManagedAgentsEnvironmentNotFoundRunError.of("message")

        assertThat(betaManagedAgentsEnvironmentNotFoundRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEnvironmentNotFoundRunError =
            BetaManagedAgentsEnvironmentNotFoundRunError.of("message")

        val roundtrippedBetaManagedAgentsEnvironmentNotFoundRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEnvironmentNotFoundRunError),
                jacksonTypeRef<BetaManagedAgentsEnvironmentNotFoundRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEnvironmentNotFoundRunError)
            .isEqualTo(betaManagedAgentsEnvironmentNotFoundRunError)
    }
}
