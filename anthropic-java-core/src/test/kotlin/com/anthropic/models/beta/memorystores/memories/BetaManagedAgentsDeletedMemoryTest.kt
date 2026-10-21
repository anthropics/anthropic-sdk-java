package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeletedMemoryTest {

    @Test
    fun create() {
        val betaManagedAgentsDeletedMemory = BetaManagedAgentsDeletedMemory.of("id")

        assertThat(betaManagedAgentsDeletedMemory.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeletedMemory = BetaManagedAgentsDeletedMemory.of("id")

        val roundtrippedBetaManagedAgentsDeletedMemory =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeletedMemory),
                jacksonTypeRef<BetaManagedAgentsDeletedMemory>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeletedMemory)
            .isEqualTo(betaManagedAgentsDeletedMemory)
    }
}
