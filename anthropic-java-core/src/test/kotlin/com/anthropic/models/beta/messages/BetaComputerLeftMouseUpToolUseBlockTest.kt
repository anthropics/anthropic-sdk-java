package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerLeftMouseUpToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerLeftMouseUpToolUseBlock =
            BetaComputerLeftMouseUpToolUseBlock.builder()
                .id("id")
                .input(BetaComputerLeftMouseUpInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerLeftMouseUpToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerLeftMouseUpToolUseBlock.input())
            .isEqualTo(BetaComputerLeftMouseUpInput.builder().build())
        assertThat(betaComputerLeftMouseUpToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerLeftMouseUpToolUseBlock =
            BetaComputerLeftMouseUpToolUseBlock.builder()
                .id("id")
                .input(BetaComputerLeftMouseUpInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerLeftMouseUpToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerLeftMouseUpToolUseBlock),
                jacksonTypeRef<BetaComputerLeftMouseUpToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerLeftMouseUpToolUseBlock)
            .isEqualTo(betaComputerLeftMouseUpToolUseBlock)
    }
}
