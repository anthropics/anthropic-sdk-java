package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDreamOutputTest {

    @Test
    fun create() {
        val betaDreamOutput = BetaDreamOutput.of("memory_store_id")

        assertThat(betaDreamOutput.memoryStoreId()).isEqualTo("memory_store_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamOutput = BetaDreamOutput.of("memory_store_id")

        val roundtrippedBetaDreamOutput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamOutput),
                jacksonTypeRef<BetaDreamOutput>(),
            )

        assertThat(roundtrippedBetaDreamOutput).isEqualTo(betaDreamOutput)
    }
}
