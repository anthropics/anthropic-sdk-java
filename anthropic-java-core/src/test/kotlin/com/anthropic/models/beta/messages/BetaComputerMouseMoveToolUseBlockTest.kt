package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerMouseMoveToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerMouseMoveToolUseBlock =
            BetaComputerMouseMoveToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerMouseMoveInput.builder().addCoordinate(0L).addCoordinate(0L).build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerMouseMoveToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerMouseMoveToolUseBlock.input())
            .isEqualTo(
                BetaComputerMouseMoveInput.builder().addCoordinate(0L).addCoordinate(0L).build()
            )
        assertThat(betaComputerMouseMoveToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerMouseMoveToolUseBlock =
            BetaComputerMouseMoveToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerMouseMoveInput.builder().addCoordinate(0L).addCoordinate(0L).build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerMouseMoveToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerMouseMoveToolUseBlock),
                jacksonTypeRef<BetaComputerMouseMoveToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerMouseMoveToolUseBlock)
            .isEqualTo(betaComputerMouseMoveToolUseBlock)
    }
}
