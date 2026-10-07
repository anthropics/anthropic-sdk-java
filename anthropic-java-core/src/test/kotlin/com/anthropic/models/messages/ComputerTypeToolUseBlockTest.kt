package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerTypeToolUseBlockTest {

    @Test
    fun create() {
        val computerTypeToolUseBlock =
            ComputerTypeToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerTypeInput.of("text"))
                .build()

        assertThat(computerTypeToolUseBlock.id()).isEqualTo("id")
        assertThat(computerTypeToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerTypeToolUseBlock.input()).isEqualTo(ComputerTypeInput.of("text"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerTypeToolUseBlock =
            ComputerTypeToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerTypeInput.of("text"))
                .build()

        val roundtrippedComputerTypeToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerTypeToolUseBlock),
                jacksonTypeRef<ComputerTypeToolUseBlock>(),
            )

        assertThat(roundtrippedComputerTypeToolUseBlock).isEqualTo(computerTypeToolUseBlock)
    }
}
