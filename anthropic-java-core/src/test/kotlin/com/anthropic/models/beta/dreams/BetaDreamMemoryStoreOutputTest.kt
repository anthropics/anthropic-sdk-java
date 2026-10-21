package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDreamMemoryStoreOutputTest {

    @Test
    fun create() {
        val betaDreamMemoryStoreOutput = BetaDreamMemoryStoreOutput.of("memory_store_id")

        assertThat(betaDreamMemoryStoreOutput.memoryStoreId()).isEqualTo("memory_store_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamMemoryStoreOutput = BetaDreamMemoryStoreOutput.of("memory_store_id")

        val roundtrippedBetaDreamMemoryStoreOutput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamMemoryStoreOutput),
                jacksonTypeRef<BetaDreamMemoryStoreOutput>(),
            )

        assertThat(roundtrippedBetaDreamMemoryStoreOutput).isEqualTo(betaDreamMemoryStoreOutput)
    }
}
