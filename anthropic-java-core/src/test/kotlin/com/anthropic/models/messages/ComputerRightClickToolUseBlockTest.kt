package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerRightClickToolUseBlockTest {

    @Test
    fun create() {
        val computerRightClickToolUseBlock =
            ComputerRightClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerRightClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        assertThat(computerRightClickToolUseBlock.id()).isEqualTo("id")
        assertThat(computerRightClickToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerRightClickToolUseBlock.input())
            .isEqualTo(
                ComputerRightClickInput.builder()
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .text("text")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerRightClickToolUseBlock =
            ComputerRightClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerRightClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val roundtrippedComputerRightClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerRightClickToolUseBlock),
                jacksonTypeRef<ComputerRightClickToolUseBlock>(),
            )

        assertThat(roundtrippedComputerRightClickToolUseBlock)
            .isEqualTo(computerRightClickToolUseBlock)
    }
}
