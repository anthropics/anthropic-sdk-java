package com.anthropic.models.beta.memorystores

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeletedMemoryStoreTest {

    @Test
    fun create() {
        val betaManagedAgentsDeletedMemoryStore = BetaManagedAgentsDeletedMemoryStore.of("id")

        assertThat(betaManagedAgentsDeletedMemoryStore.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeletedMemoryStore = BetaManagedAgentsDeletedMemoryStore.of("id")

        val roundtrippedBetaManagedAgentsDeletedMemoryStore =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeletedMemoryStore),
                jacksonTypeRef<BetaManagedAgentsDeletedMemoryStore>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeletedMemoryStore)
            .isEqualTo(betaManagedAgentsDeletedMemoryStore)
    }
}
