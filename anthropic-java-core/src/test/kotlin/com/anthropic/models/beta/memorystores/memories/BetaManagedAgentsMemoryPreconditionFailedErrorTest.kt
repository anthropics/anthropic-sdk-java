package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryPreconditionFailedErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryPreconditionFailedError =
            BetaManagedAgentsMemoryPreconditionFailedError.builder().message("message").build()

        assertThat(betaManagedAgentsMemoryPreconditionFailedError.message()).contains("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryPreconditionFailedError =
            BetaManagedAgentsMemoryPreconditionFailedError.builder().message("message").build()

        val roundtrippedBetaManagedAgentsMemoryPreconditionFailedError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryPreconditionFailedError),
                jacksonTypeRef<BetaManagedAgentsMemoryPreconditionFailedError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryPreconditionFailedError)
            .isEqualTo(betaManagedAgentsMemoryPreconditionFailedError)
    }
}
