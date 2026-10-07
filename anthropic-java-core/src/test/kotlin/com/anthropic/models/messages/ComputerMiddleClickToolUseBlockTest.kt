package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerMiddleClickToolUseBlockTest {

    @Test
    fun create() {
        val computerMiddleClickToolUseBlock =
            ComputerMiddleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerMiddleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        assertThat(computerMiddleClickToolUseBlock.id()).isEqualTo("id")
        assertThat(computerMiddleClickToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerMiddleClickToolUseBlock.input())
            .isEqualTo(
                ComputerMiddleClickInput.builder()
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .text("text")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerMiddleClickToolUseBlock =
            ComputerMiddleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerMiddleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val roundtrippedComputerMiddleClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerMiddleClickToolUseBlock),
                jacksonTypeRef<ComputerMiddleClickToolUseBlock>(),
            )

        assertThat(roundtrippedComputerMiddleClickToolUseBlock)
            .isEqualTo(computerMiddleClickToolUseBlock)
    }
}
