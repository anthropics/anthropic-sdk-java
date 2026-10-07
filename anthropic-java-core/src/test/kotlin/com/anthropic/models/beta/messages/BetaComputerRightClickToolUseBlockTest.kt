package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerRightClickToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerRightClickToolUseBlock =
            BetaComputerRightClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerRightClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerRightClickToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerRightClickToolUseBlock.input())
            .isEqualTo(
                BetaComputerRightClickInput.builder()
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .text("text")
                    .build()
            )
        assertThat(betaComputerRightClickToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerRightClickToolUseBlock =
            BetaComputerRightClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerRightClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerRightClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerRightClickToolUseBlock),
                jacksonTypeRef<BetaComputerRightClickToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerRightClickToolUseBlock)
            .isEqualTo(betaComputerRightClickToolUseBlock)
    }
}
