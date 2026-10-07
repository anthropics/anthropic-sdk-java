package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerKeyToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerKeyToolUseBlock =
            BetaComputerKeyToolUseBlock.builder()
                .id("id")
                .input(BetaComputerKeyInput.builder().text("text").repeat(1L).build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerKeyToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerKeyToolUseBlock.input())
            .isEqualTo(BetaComputerKeyInput.builder().text("text").repeat(1L).build())
        assertThat(betaComputerKeyToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerKeyToolUseBlock =
            BetaComputerKeyToolUseBlock.builder()
                .id("id")
                .input(BetaComputerKeyInput.builder().text("text").repeat(1L).build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerKeyToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerKeyToolUseBlock),
                jacksonTypeRef<BetaComputerKeyToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerKeyToolUseBlock).isEqualTo(betaComputerKeyToolUseBlock)
    }
}
