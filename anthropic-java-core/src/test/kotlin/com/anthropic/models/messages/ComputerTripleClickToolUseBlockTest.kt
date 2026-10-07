package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerTripleClickToolUseBlockTest {

    @Test
    fun create() {
        val computerTripleClickToolUseBlock =
            ComputerTripleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerTripleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        assertThat(computerTripleClickToolUseBlock.id()).isEqualTo("id")
        assertThat(computerTripleClickToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerTripleClickToolUseBlock.input())
            .isEqualTo(
                ComputerTripleClickInput.builder()
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .text("text")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerTripleClickToolUseBlock =
            ComputerTripleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerTripleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val roundtrippedComputerTripleClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerTripleClickToolUseBlock),
                jacksonTypeRef<ComputerTripleClickToolUseBlock>(),
            )

        assertThat(roundtrippedComputerTripleClickToolUseBlock)
            .isEqualTo(computerTripleClickToolUseBlock)
    }
}
