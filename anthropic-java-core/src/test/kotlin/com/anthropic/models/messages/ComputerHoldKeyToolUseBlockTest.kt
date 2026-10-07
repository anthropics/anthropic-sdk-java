package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerHoldKeyToolUseBlockTest {

    @Test
    fun create() {
        val computerHoldKeyToolUseBlock =
            ComputerHoldKeyToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerHoldKeyInput.builder().duration(300L).text("text").build())
                .build()

        assertThat(computerHoldKeyToolUseBlock.id()).isEqualTo("id")
        assertThat(computerHoldKeyToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerHoldKeyToolUseBlock.input())
            .isEqualTo(ComputerHoldKeyInput.builder().duration(300L).text("text").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerHoldKeyToolUseBlock =
            ComputerHoldKeyToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerHoldKeyInput.builder().duration(300L).text("text").build())
                .build()

        val roundtrippedComputerHoldKeyToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerHoldKeyToolUseBlock),
                jacksonTypeRef<ComputerHoldKeyToolUseBlock>(),
            )

        assertThat(roundtrippedComputerHoldKeyToolUseBlock).isEqualTo(computerHoldKeyToolUseBlock)
    }
}
