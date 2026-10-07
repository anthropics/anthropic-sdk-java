package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerTypeToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerTypeToolUseBlock =
            BetaComputerTypeToolUseBlock.builder()
                .id("id")
                .input(BetaComputerTypeInput.of("text"))
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerTypeToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerTypeToolUseBlock.input()).isEqualTo(BetaComputerTypeInput.of("text"))
        assertThat(betaComputerTypeToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerTypeToolUseBlock =
            BetaComputerTypeToolUseBlock.builder()
                .id("id")
                .input(BetaComputerTypeInput.of("text"))
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerTypeToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerTypeToolUseBlock),
                jacksonTypeRef<BetaComputerTypeToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerTypeToolUseBlock).isEqualTo(betaComputerTypeToolUseBlock)
    }
}
