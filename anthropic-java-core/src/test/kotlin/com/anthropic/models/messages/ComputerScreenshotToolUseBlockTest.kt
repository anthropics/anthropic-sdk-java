package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerScreenshotToolUseBlockTest {

    @Test
    fun create() {
        val computerScreenshotToolUseBlock =
            ComputerScreenshotToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerScreenshotInput.builder().build())
                .build()

        assertThat(computerScreenshotToolUseBlock.id()).isEqualTo("id")
        assertThat(computerScreenshotToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(computerScreenshotToolUseBlock.input())
            .isEqualTo(ComputerScreenshotInput.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerScreenshotToolUseBlock =
            ComputerScreenshotToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerScreenshotInput.builder().build())
                .build()

        val roundtrippedComputerScreenshotToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerScreenshotToolUseBlock),
                jacksonTypeRef<ComputerScreenshotToolUseBlock>(),
            )

        assertThat(roundtrippedComputerScreenshotToolUseBlock)
            .isEqualTo(computerScreenshotToolUseBlock)
    }
}
