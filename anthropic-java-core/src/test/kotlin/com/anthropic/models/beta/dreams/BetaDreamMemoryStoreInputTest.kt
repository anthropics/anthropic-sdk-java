package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDreamMemoryStoreInputTest {

    @Test
    fun create() {
        val betaDreamMemoryStoreInput = BetaDreamMemoryStoreInput.of("x")

        assertThat(betaDreamMemoryStoreInput.memoryStoreId()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamMemoryStoreInput = BetaDreamMemoryStoreInput.of("x")

        val roundtrippedBetaDreamMemoryStoreInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamMemoryStoreInput),
                jacksonTypeRef<BetaDreamMemoryStoreInput>(),
            )

        assertThat(roundtrippedBetaDreamMemoryStoreInput).isEqualTo(betaDreamMemoryStoreInput)
    }
}
