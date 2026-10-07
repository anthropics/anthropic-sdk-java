package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerScreenshotToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerScreenshotToolUseBlock =
            BetaComputerScreenshotToolUseBlock.builder()
                .id("id")
                .input(BetaComputerScreenshotInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerScreenshotToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerScreenshotToolUseBlock.input())
            .isEqualTo(BetaComputerScreenshotInput.builder().build())
        assertThat(betaComputerScreenshotToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerScreenshotToolUseBlock =
            BetaComputerScreenshotToolUseBlock.builder()
                .id("id")
                .input(BetaComputerScreenshotInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerScreenshotToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerScreenshotToolUseBlock),
                jacksonTypeRef<BetaComputerScreenshotToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerScreenshotToolUseBlock)
            .isEqualTo(betaComputerScreenshotToolUseBlock)
    }
}
