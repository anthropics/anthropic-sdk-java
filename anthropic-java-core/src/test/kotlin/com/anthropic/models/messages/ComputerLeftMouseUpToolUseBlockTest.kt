package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerLeftMouseUpToolUseBlockTest {

    @Test
    fun create() {
        val computerLeftMouseUpToolUseBlock =
            ComputerLeftMouseUpToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerLeftMouseUpInput.builder().build())
                .build()

        assertThat(computerLeftMouseUpToolUseBlock.id()).isEqualTo("id")
        assertThat(computerLeftMouseUpToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerLeftMouseUpToolUseBlock.input())
            .isEqualTo(ComputerLeftMouseUpInput.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerLeftMouseUpToolUseBlock =
            ComputerLeftMouseUpToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerLeftMouseUpInput.builder().build())
                .build()

        val roundtrippedComputerLeftMouseUpToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerLeftMouseUpToolUseBlock),
                jacksonTypeRef<ComputerLeftMouseUpToolUseBlock>(),
            )

        assertThat(roundtrippedComputerLeftMouseUpToolUseBlock)
            .isEqualTo(computerLeftMouseUpToolUseBlock)
    }
}
