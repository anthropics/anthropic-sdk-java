package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerLeftClickToolUseBlockTest {

    @Test
    fun create() {
        val computerLeftClickToolUseBlock =
            ComputerLeftClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerLeftClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        assertThat(computerLeftClickToolUseBlock.id()).isEqualTo("id")
        assertThat(computerLeftClickToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerLeftClickToolUseBlock.input())
            .isEqualTo(
                ComputerLeftClickInput.builder()
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .text("text")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerLeftClickToolUseBlock =
            ComputerLeftClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerLeftClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val roundtrippedComputerLeftClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerLeftClickToolUseBlock),
                jacksonTypeRef<ComputerLeftClickToolUseBlock>(),
            )

        assertThat(roundtrippedComputerLeftClickToolUseBlock)
            .isEqualTo(computerLeftClickToolUseBlock)
    }
}
