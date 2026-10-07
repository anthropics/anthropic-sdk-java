package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerLeftMouseDownToolUseBlockTest {

    @Test
    fun create() {
        val computerLeftMouseDownToolUseBlock =
            ComputerLeftMouseDownToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerLeftMouseDownInput.builder().build())
                .build()

        assertThat(computerLeftMouseDownToolUseBlock.id()).isEqualTo("id")
        assertThat(computerLeftMouseDownToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerLeftMouseDownToolUseBlock.input())
            .isEqualTo(ComputerLeftMouseDownInput.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerLeftMouseDownToolUseBlock =
            ComputerLeftMouseDownToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerLeftMouseDownInput.builder().build())
                .build()

        val roundtrippedComputerLeftMouseDownToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerLeftMouseDownToolUseBlock),
                jacksonTypeRef<ComputerLeftMouseDownToolUseBlock>(),
            )

        assertThat(roundtrippedComputerLeftMouseDownToolUseBlock)
            .isEqualTo(computerLeftMouseDownToolUseBlock)
    }
}
