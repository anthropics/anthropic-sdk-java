// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDreamMemoryStoreOutputTest {

    @Test
    fun create() {
        val betaDreamMemoryStoreOutput =
            BetaDreamMemoryStoreOutput.builder()
                .memoryStoreId("memory_store_id")
                .type(BetaDreamMemoryStoreOutput.Type.MEMORY_STORE)
                .build()

        assertThat(betaDreamMemoryStoreOutput.memoryStoreId()).isEqualTo("memory_store_id")
        assertThat(betaDreamMemoryStoreOutput.type())
            .isEqualTo(BetaDreamMemoryStoreOutput.Type.MEMORY_STORE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamMemoryStoreOutput =
            BetaDreamMemoryStoreOutput.builder()
                .memoryStoreId("memory_store_id")
                .type(BetaDreamMemoryStoreOutput.Type.MEMORY_STORE)
                .build()

        val roundtrippedBetaDreamMemoryStoreOutput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamMemoryStoreOutput),
                jacksonTypeRef<BetaDreamMemoryStoreOutput>(),
            )

        assertThat(roundtrippedBetaDreamMemoryStoreOutput).isEqualTo(betaDreamMemoryStoreOutput)
    }
}
