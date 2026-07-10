// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDreamOutputTest {

    @Test
    fun create() {
        val betaDreamOutput =
            BetaDreamOutput.builder()
                .memoryStoreId("memory_store_id")
                .type(BetaDreamOutput.Type.MEMORY_STORE)
                .build()

        assertThat(betaDreamOutput.memoryStoreId()).isEqualTo("memory_store_id")
        assertThat(betaDreamOutput.type()).isEqualTo(BetaDreamOutput.Type.MEMORY_STORE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamOutput =
            BetaDreamOutput.builder()
                .memoryStoreId("memory_store_id")
                .type(BetaDreamOutput.Type.MEMORY_STORE)
                .build()

        val roundtrippedBetaDreamOutput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamOutput),
                jacksonTypeRef<BetaDreamOutput>(),
            )

        assertThat(roundtrippedBetaDreamOutput).isEqualTo(betaDreamOutput)
    }
}
