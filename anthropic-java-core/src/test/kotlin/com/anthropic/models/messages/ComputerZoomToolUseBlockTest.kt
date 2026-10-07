package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerZoomToolUseBlockTest {

    @Test
    fun create() {
        val computerZoomToolUseBlock =
            ComputerZoomToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerZoomInput.of(listOf(0L, 0L, 0L, 0L)))
                .build()

        assertThat(computerZoomToolUseBlock.id()).isEqualTo("id")
        assertThat(computerZoomToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerZoomToolUseBlock.input())
            .isEqualTo(ComputerZoomInput.of(listOf(0L, 0L, 0L, 0L)))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerZoomToolUseBlock =
            ComputerZoomToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerZoomInput.of(listOf(0L, 0L, 0L, 0L)))
                .build()

        val roundtrippedComputerZoomToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerZoomToolUseBlock),
                jacksonTypeRef<ComputerZoomToolUseBlock>(),
            )

        assertThat(roundtrippedComputerZoomToolUseBlock).isEqualTo(computerZoomToolUseBlock)
    }
}
