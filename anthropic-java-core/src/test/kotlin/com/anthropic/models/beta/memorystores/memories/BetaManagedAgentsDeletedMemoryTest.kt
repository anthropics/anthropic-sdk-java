// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeletedMemoryTest {

    @Test
    fun create() {
        val betaManagedAgentsDeletedMemory =
            BetaManagedAgentsDeletedMemory.builder()
                .id("id")
                .type(BetaManagedAgentsDeletedMemory.Type.MEMORY_DELETED)
                .build()

        assertThat(betaManagedAgentsDeletedMemory.id()).isEqualTo("id")
        assertThat(betaManagedAgentsDeletedMemory.type())
            .isEqualTo(BetaManagedAgentsDeletedMemory.Type.MEMORY_DELETED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeletedMemory =
            BetaManagedAgentsDeletedMemory.builder()
                .id("id")
                .type(BetaManagedAgentsDeletedMemory.Type.MEMORY_DELETED)
                .build()

        val roundtrippedBetaManagedAgentsDeletedMemory =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeletedMemory),
                jacksonTypeRef<BetaManagedAgentsDeletedMemory>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeletedMemory)
            .isEqualTo(betaManagedAgentsDeletedMemory)
    }
}
