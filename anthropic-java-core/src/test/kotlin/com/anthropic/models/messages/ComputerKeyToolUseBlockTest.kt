package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerKeyToolUseBlockTest {

    @Test
    fun create() {
        val computerKeyToolUseBlock =
            ComputerKeyToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerKeyInput.builder().text("text").repeat(1L).build())
                .build()

        assertThat(computerKeyToolUseBlock.id()).isEqualTo("id")
        assertThat(computerKeyToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerKeyToolUseBlock.input())
            .isEqualTo(ComputerKeyInput.builder().text("text").repeat(1L).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerKeyToolUseBlock =
            ComputerKeyToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerKeyInput.builder().text("text").repeat(1L).build())
                .build()

        val roundtrippedComputerKeyToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerKeyToolUseBlock),
                jacksonTypeRef<ComputerKeyToolUseBlock>(),
            )

        assertThat(roundtrippedComputerKeyToolUseBlock).isEqualTo(computerKeyToolUseBlock)
    }
}
