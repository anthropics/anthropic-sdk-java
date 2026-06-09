// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryStoreResourceConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryStoreResourceConfig =
            BetaManagedAgentsMemoryStoreResourceConfig.builder()
                .memoryStoreId("memory_store_id")
                .type(BetaManagedAgentsMemoryStoreResourceConfig.Type.MEMORY_STORE)
                .access(BetaManagedAgentsMemoryStoreResourceConfig.Access.READ_WRITE)
                .instructions("instructions")
                .build()

        assertThat(betaManagedAgentsMemoryStoreResourceConfig.memoryStoreId())
            .isEqualTo("memory_store_id")
        assertThat(betaManagedAgentsMemoryStoreResourceConfig.type())
            .isEqualTo(BetaManagedAgentsMemoryStoreResourceConfig.Type.MEMORY_STORE)
        assertThat(betaManagedAgentsMemoryStoreResourceConfig.access())
            .contains(BetaManagedAgentsMemoryStoreResourceConfig.Access.READ_WRITE)
        assertThat(betaManagedAgentsMemoryStoreResourceConfig.instructions())
            .contains("instructions")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryStoreResourceConfig =
            BetaManagedAgentsMemoryStoreResourceConfig.builder()
                .memoryStoreId("memory_store_id")
                .type(BetaManagedAgentsMemoryStoreResourceConfig.Type.MEMORY_STORE)
                .access(BetaManagedAgentsMemoryStoreResourceConfig.Access.READ_WRITE)
                .instructions("instructions")
                .build()

        val roundtrippedBetaManagedAgentsMemoryStoreResourceConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryStoreResourceConfig),
                jacksonTypeRef<BetaManagedAgentsMemoryStoreResourceConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryStoreResourceConfig)
            .isEqualTo(betaManagedAgentsMemoryStoreResourceConfig)
    }
}
