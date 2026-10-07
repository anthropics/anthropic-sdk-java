package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerWaitToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerWaitToolUseBlock =
            BetaComputerWaitToolUseBlock.builder()
                .id("id")
                .input(BetaComputerWaitInput.of(300L))
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerWaitToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerWaitToolUseBlock.input()).isEqualTo(BetaComputerWaitInput.of(300L))
        assertThat(betaComputerWaitToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerWaitToolUseBlock =
            BetaComputerWaitToolUseBlock.builder()
                .id("id")
                .input(BetaComputerWaitInput.of(300L))
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerWaitToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerWaitToolUseBlock),
                jacksonTypeRef<BetaComputerWaitToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerWaitToolUseBlock).isEqualTo(betaComputerWaitToolUseBlock)
    }
}
