package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileNotFoundRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsFileNotFoundRunError =
            BetaManagedAgentsFileNotFoundRunError.of("message")

        assertThat(betaManagedAgentsFileNotFoundRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileNotFoundRunError =
            BetaManagedAgentsFileNotFoundRunError.of("message")

        val roundtrippedBetaManagedAgentsFileNotFoundRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsFileNotFoundRunError),
                jacksonTypeRef<BetaManagedAgentsFileNotFoundRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsFileNotFoundRunError)
            .isEqualTo(betaManagedAgentsFileNotFoundRunError)
    }
}
