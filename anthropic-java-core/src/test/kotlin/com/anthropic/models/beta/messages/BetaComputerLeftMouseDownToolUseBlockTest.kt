package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerLeftMouseDownToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerLeftMouseDownToolUseBlock =
            BetaComputerLeftMouseDownToolUseBlock.builder()
                .id("id")
                .input(BetaComputerLeftMouseDownInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerLeftMouseDownToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerLeftMouseDownToolUseBlock.input())
            .isEqualTo(BetaComputerLeftMouseDownInput.builder().build())
        assertThat(betaComputerLeftMouseDownToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerLeftMouseDownToolUseBlock =
            BetaComputerLeftMouseDownToolUseBlock.builder()
                .id("id")
                .input(BetaComputerLeftMouseDownInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerLeftMouseDownToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerLeftMouseDownToolUseBlock),
                jacksonTypeRef<BetaComputerLeftMouseDownToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerLeftMouseDownToolUseBlock)
            .isEqualTo(betaComputerLeftMouseDownToolUseBlock)
    }
}
