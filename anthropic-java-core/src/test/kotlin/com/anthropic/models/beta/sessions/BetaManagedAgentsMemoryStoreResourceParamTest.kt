package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryStoreResourceParamTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryStoreResourceParam =
            BetaManagedAgentsMemoryStoreResourceParam.builder()
                .memoryStoreId("memstore_c")
                .access(BetaManagedAgentsMemoryStoreResourceParam.Access.READ_WRITE)
                .instructions("instructions")
                .build()

        assertThat(betaManagedAgentsMemoryStoreResourceParam.memoryStoreId())
            .isEqualTo("memstore_c")
        assertThat(betaManagedAgentsMemoryStoreResourceParam.access())
            .contains(BetaManagedAgentsMemoryStoreResourceParam.Access.READ_WRITE)
        assertThat(betaManagedAgentsMemoryStoreResourceParam.instructions())
            .contains("instructions")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryStoreResourceParam =
            BetaManagedAgentsMemoryStoreResourceParam.builder()
                .memoryStoreId("memstore_c")
                .access(BetaManagedAgentsMemoryStoreResourceParam.Access.READ_WRITE)
                .instructions("instructions")
                .build()

        val roundtrippedBetaManagedAgentsMemoryStoreResourceParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryStoreResourceParam),
                jacksonTypeRef<BetaManagedAgentsMemoryStoreResourceParam>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryStoreResourceParam)
            .isEqualTo(betaManagedAgentsMemoryStoreResourceParam)
    }
}
