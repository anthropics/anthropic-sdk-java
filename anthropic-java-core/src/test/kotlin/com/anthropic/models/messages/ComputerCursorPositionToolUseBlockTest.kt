package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerCursorPositionToolUseBlockTest {

    @Test
    fun create() {
        val computerCursorPositionToolUseBlock =
            ComputerCursorPositionToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerCursorPositionInput.builder().build())
                .build()

        assertThat(computerCursorPositionToolUseBlock.id()).isEqualTo("id")
        assertThat(computerCursorPositionToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerCursorPositionToolUseBlock.input())
            .isEqualTo(ComputerCursorPositionInput.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerCursorPositionToolUseBlock =
            ComputerCursorPositionToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerCursorPositionInput.builder().build())
                .build()

        val roundtrippedComputerCursorPositionToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerCursorPositionToolUseBlock),
                jacksonTypeRef<ComputerCursorPositionToolUseBlock>(),
            )

        assertThat(roundtrippedComputerCursorPositionToolUseBlock)
            .isEqualTo(computerCursorPositionToolUseBlock)
    }
}
