package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerZoomInputTest {

    @Test
    fun create() {
        val computerZoomInput = ComputerZoomInput.of(listOf(0L, 0L, 0L, 0L))

        assertThat(computerZoomInput.region()).containsExactly(0L, 0L, 0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerZoomInput = ComputerZoomInput.of(listOf(0L, 0L, 0L, 0L))

        val roundtrippedComputerZoomInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerZoomInput),
                jacksonTypeRef<ComputerZoomInput>(),
            )

        assertThat(roundtrippedComputerZoomInput).isEqualTo(computerZoomInput)
    }
}
