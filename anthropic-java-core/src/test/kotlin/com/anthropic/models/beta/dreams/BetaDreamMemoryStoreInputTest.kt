// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDreamMemoryStoreInputTest {

    @Test
    fun create() {
        val betaDreamMemoryStoreInput =
            BetaDreamMemoryStoreInput.builder()
                .memoryStoreId("x")
                .type(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
                .build()

        assertThat(betaDreamMemoryStoreInput.memoryStoreId()).isEqualTo("x")
        assertThat(betaDreamMemoryStoreInput.type())
            .isEqualTo(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamMemoryStoreInput =
            BetaDreamMemoryStoreInput.builder()
                .memoryStoreId("x")
                .type(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
                .build()

        val roundtrippedBetaDreamMemoryStoreInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamMemoryStoreInput),
                jacksonTypeRef<BetaDreamMemoryStoreInput>(),
            )

        assertThat(roundtrippedBetaDreamMemoryStoreInput).isEqualTo(betaDreamMemoryStoreInput)
    }
}
