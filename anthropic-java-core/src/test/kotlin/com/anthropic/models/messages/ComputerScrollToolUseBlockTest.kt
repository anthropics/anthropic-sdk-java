package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerScrollToolUseBlockTest {

    @Test
    fun create() {
        val computerScrollToolUseBlock =
            ComputerScrollToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerScrollInput.builder()
                        .scrollAmount(0L)
                        .scrollDirection(ComputerScrollDirection.UP)
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        assertThat(computerScrollToolUseBlock.id()).isEqualTo("id")
        assertThat(computerScrollToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerScrollToolUseBlock.input())
            .isEqualTo(
                ComputerScrollInput.builder()
                    .scrollAmount(0L)
                    .scrollDirection(ComputerScrollDirection.UP)
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .text("text")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerScrollToolUseBlock =
            ComputerScrollToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerScrollInput.builder()
                        .scrollAmount(0L)
                        .scrollDirection(ComputerScrollDirection.UP)
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val roundtrippedComputerScrollToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerScrollToolUseBlock),
                jacksonTypeRef<ComputerScrollToolUseBlock>(),
            )

        assertThat(roundtrippedComputerScrollToolUseBlock).isEqualTo(computerScrollToolUseBlock)
    }
}
