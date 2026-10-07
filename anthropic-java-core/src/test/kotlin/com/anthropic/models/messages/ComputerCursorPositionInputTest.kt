package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerCursorPositionInputTest {

    @Test
    fun create() {
        val computerCursorPositionInput = ComputerCursorPositionInput.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerCursorPositionInput = ComputerCursorPositionInput.builder().build()

        val roundtrippedComputerCursorPositionInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerCursorPositionInput),
                jacksonTypeRef<ComputerCursorPositionInput>(),
            )

        assertThat(roundtrippedComputerCursorPositionInput).isEqualTo(computerCursorPositionInput)
    }
}
