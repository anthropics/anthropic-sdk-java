package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerMouseMoveToolUseBlockTest {

    @Test
    fun create() {
        val computerMouseMoveToolUseBlock =
            ComputerMouseMoveToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerMouseMoveInput.builder().addCoordinate(0L).addCoordinate(0L).build())
                .build()

        assertThat(computerMouseMoveToolUseBlock.id()).isEqualTo("id")
        assertThat(computerMouseMoveToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerMouseMoveToolUseBlock.input())
            .isEqualTo(ComputerMouseMoveInput.builder().addCoordinate(0L).addCoordinate(0L).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerMouseMoveToolUseBlock =
            ComputerMouseMoveToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerMouseMoveInput.builder().addCoordinate(0L).addCoordinate(0L).build())
                .build()

        val roundtrippedComputerMouseMoveToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerMouseMoveToolUseBlock),
                jacksonTypeRef<ComputerMouseMoveToolUseBlock>(),
            )

        assertThat(roundtrippedComputerMouseMoveToolUseBlock)
            .isEqualTo(computerMouseMoveToolUseBlock)
    }
}
