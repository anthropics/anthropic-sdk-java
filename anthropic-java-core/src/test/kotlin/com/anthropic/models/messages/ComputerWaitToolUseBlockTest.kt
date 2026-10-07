package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerWaitToolUseBlockTest {

    @Test
    fun create() {
        val computerWaitToolUseBlock =
            ComputerWaitToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerWaitInput.of(300L))
                .build()

        assertThat(computerWaitToolUseBlock.id()).isEqualTo("id")
        assertThat(computerWaitToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerWaitToolUseBlock.input()).isEqualTo(ComputerWaitInput.of(300L))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerWaitToolUseBlock =
            ComputerWaitToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerWaitInput.of(300L))
                .build()

        val roundtrippedComputerWaitToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerWaitToolUseBlock),
                jacksonTypeRef<ComputerWaitToolUseBlock>(),
            )

        assertThat(roundtrippedComputerWaitToolUseBlock).isEqualTo(computerWaitToolUseBlock)
    }
}
