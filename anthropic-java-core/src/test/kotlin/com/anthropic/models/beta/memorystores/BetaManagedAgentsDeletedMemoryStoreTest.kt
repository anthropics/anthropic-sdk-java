// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeletedMemoryStoreTest {

    @Test
    fun create() {
        val betaManagedAgentsDeletedMemoryStore =
            BetaManagedAgentsDeletedMemoryStore.builder()
                .id("id")
                .type(BetaManagedAgentsDeletedMemoryStore.Type.MEMORY_STORE_DELETED)
                .build()

        assertThat(betaManagedAgentsDeletedMemoryStore.id()).isEqualTo("id")
        assertThat(betaManagedAgentsDeletedMemoryStore.type())
            .isEqualTo(BetaManagedAgentsDeletedMemoryStore.Type.MEMORY_STORE_DELETED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeletedMemoryStore =
            BetaManagedAgentsDeletedMemoryStore.builder()
                .id("id")
                .type(BetaManagedAgentsDeletedMemoryStore.Type.MEMORY_STORE_DELETED)
                .build()

        val roundtrippedBetaManagedAgentsDeletedMemoryStore =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeletedMemoryStore),
                jacksonTypeRef<BetaManagedAgentsDeletedMemoryStore>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeletedMemoryStore)
            .isEqualTo(betaManagedAgentsDeletedMemoryStore)
    }
}
