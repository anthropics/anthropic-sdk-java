package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryPrefixTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryPrefix = BetaManagedAgentsMemoryPrefix.of("path")

        assertThat(betaManagedAgentsMemoryPrefix.path()).isEqualTo("path")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryPrefix = BetaManagedAgentsMemoryPrefix.of("path")

        val roundtrippedBetaManagedAgentsMemoryPrefix =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryPrefix),
                jacksonTypeRef<BetaManagedAgentsMemoryPrefix>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryPrefix)
            .isEqualTo(betaManagedAgentsMemoryPrefix)
    }
}
