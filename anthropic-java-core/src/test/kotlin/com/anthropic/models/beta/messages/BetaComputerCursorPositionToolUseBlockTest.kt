package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerCursorPositionToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerCursorPositionToolUseBlock =
            BetaComputerCursorPositionToolUseBlock.builder()
                .id("id")
                .input(BetaComputerCursorPositionInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerCursorPositionToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerCursorPositionToolUseBlock.input())
            .isEqualTo(BetaComputerCursorPositionInput.builder().build())
        assertThat(betaComputerCursorPositionToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerCursorPositionToolUseBlock =
            BetaComputerCursorPositionToolUseBlock.builder()
                .id("id")
                .input(BetaComputerCursorPositionInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerCursorPositionToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerCursorPositionToolUseBlock),
                jacksonTypeRef<BetaComputerCursorPositionToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerCursorPositionToolUseBlock)
            .isEqualTo(betaComputerCursorPositionToolUseBlock)
    }
}
