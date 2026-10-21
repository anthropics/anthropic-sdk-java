package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEnvironmentArchivedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsEnvironmentArchivedRunError =
            BetaManagedAgentsEnvironmentArchivedRunError.of("message")

        assertThat(betaManagedAgentsEnvironmentArchivedRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEnvironmentArchivedRunError =
            BetaManagedAgentsEnvironmentArchivedRunError.of("message")

        val roundtrippedBetaManagedAgentsEnvironmentArchivedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEnvironmentArchivedRunError),
                jacksonTypeRef<BetaManagedAgentsEnvironmentArchivedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEnvironmentArchivedRunError)
            .isEqualTo(betaManagedAgentsEnvironmentArchivedRunError)
    }
}
