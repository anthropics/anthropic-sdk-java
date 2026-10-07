package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerLeftClickDragToolUseBlockTest {

    @Test
    fun create() {
        val computerLeftClickDragToolUseBlock =
            ComputerLeftClickDragToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerLeftClickDragInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .addStartCoordinate(0L)
                        .addStartCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        assertThat(computerLeftClickDragToolUseBlock.id()).isEqualTo("id")
        assertThat(computerLeftClickDragToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerLeftClickDragToolUseBlock.input())
            .isEqualTo(
                ComputerLeftClickDragInput.builder()
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .addStartCoordinate(0L)
                    .addStartCoordinate(0L)
                    .text("text")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerLeftClickDragToolUseBlock =
            ComputerLeftClickDragToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerLeftClickDragInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .addStartCoordinate(0L)
                        .addStartCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val roundtrippedComputerLeftClickDragToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerLeftClickDragToolUseBlock),
                jacksonTypeRef<ComputerLeftClickDragToolUseBlock>(),
            )

        assertThat(roundtrippedComputerLeftClickDragToolUseBlock)
            .isEqualTo(computerLeftClickDragToolUseBlock)
    }
}
