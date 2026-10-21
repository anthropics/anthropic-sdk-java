package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUnknownRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsUnknownRunError = BetaManagedAgentsUnknownRunError.of("message")

        assertThat(betaManagedAgentsUnknownRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUnknownRunError = BetaManagedAgentsUnknownRunError.of("message")

        val roundtrippedBetaManagedAgentsUnknownRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUnknownRunError),
                jacksonTypeRef<BetaManagedAgentsUnknownRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUnknownRunError)
            .isEqualTo(betaManagedAgentsUnknownRunError)
    }
}
