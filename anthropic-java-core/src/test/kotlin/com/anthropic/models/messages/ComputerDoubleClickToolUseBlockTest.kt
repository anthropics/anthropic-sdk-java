package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerDoubleClickToolUseBlockTest {

    @Test
    fun create() {
        val computerDoubleClickToolUseBlock =
            ComputerDoubleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerDoubleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        assertThat(computerDoubleClickToolUseBlock.id()).isEqualTo("id")
        assertThat(computerDoubleClickToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerDoubleClickToolUseBlock.input())
            .isEqualTo(
                ComputerDoubleClickInput.builder()
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .text("text")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerDoubleClickToolUseBlock =
            ComputerDoubleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerDoubleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val roundtrippedComputerDoubleClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerDoubleClickToolUseBlock),
                jacksonTypeRef<ComputerDoubleClickToolUseBlock>(),
            )

        assertThat(roundtrippedComputerDoubleClickToolUseBlock)
            .isEqualTo(computerDoubleClickToolUseBlock)
    }
}
