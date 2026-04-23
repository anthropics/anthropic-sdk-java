// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryPrefixTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryPrefix =
            BetaManagedAgentsMemoryPrefix.builder()
                .path("path")
                .type(BetaManagedAgentsMemoryPrefix.Type.MEMORY_PREFIX)
                .build()

        assertThat(betaManagedAgentsMemoryPrefix.path()).isEqualTo("path")
        assertThat(betaManagedAgentsMemoryPrefix.type())
            .isEqualTo(BetaManagedAgentsMemoryPrefix.Type.MEMORY_PREFIX)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryPrefix =
            BetaManagedAgentsMemoryPrefix.builder()
                .path("path")
                .type(BetaManagedAgentsMemoryPrefix.Type.MEMORY_PREFIX)
                .build()

        val roundtrippedBetaManagedAgentsMemoryPrefix =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryPrefix),
                jacksonTypeRef<BetaManagedAgentsMemoryPrefix>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryPrefix)
            .isEqualTo(betaManagedAgentsMemoryPrefix)
    }
}
