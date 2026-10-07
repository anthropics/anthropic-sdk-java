package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerLeftClickToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerLeftClickToolUseBlock =
            BetaComputerLeftClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerLeftClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerLeftClickToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerLeftClickToolUseBlock.input())
            .isEqualTo(
                BetaComputerLeftClickInput.builder()
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .text("text")
                    .build()
            )
        assertThat(betaComputerLeftClickToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerLeftClickToolUseBlock =
            BetaComputerLeftClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerLeftClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerLeftClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerLeftClickToolUseBlock),
                jacksonTypeRef<BetaComputerLeftClickToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerLeftClickToolUseBlock)
            .isEqualTo(betaComputerLeftClickToolUseBlock)
    }
}
