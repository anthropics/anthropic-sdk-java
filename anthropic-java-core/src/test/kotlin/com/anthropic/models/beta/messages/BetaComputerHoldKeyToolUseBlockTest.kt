package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerHoldKeyToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerHoldKeyToolUseBlock =
            BetaComputerHoldKeyToolUseBlock.builder()
                .id("id")
                .input(BetaComputerHoldKeyInput.builder().duration(300L).text("text").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerHoldKeyToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerHoldKeyToolUseBlock.input())
            .isEqualTo(BetaComputerHoldKeyInput.builder().duration(300L).text("text").build())
        assertThat(betaComputerHoldKeyToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerHoldKeyToolUseBlock =
            BetaComputerHoldKeyToolUseBlock.builder()
                .id("id")
                .input(BetaComputerHoldKeyInput.builder().duration(300L).text("text").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerHoldKeyToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerHoldKeyToolUseBlock),
                jacksonTypeRef<BetaComputerHoldKeyToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerHoldKeyToolUseBlock)
            .isEqualTo(betaComputerHoldKeyToolUseBlock)
    }
}
