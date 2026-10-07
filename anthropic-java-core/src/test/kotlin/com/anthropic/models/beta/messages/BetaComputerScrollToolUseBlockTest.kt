package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerScrollToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerScrollToolUseBlock =
            BetaComputerScrollToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerScrollInput.builder()
                        .scrollAmount(0L)
                        .scrollDirection(BetaComputerScrollDirection.UP)
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerScrollToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerScrollToolUseBlock.input())
            .isEqualTo(
                BetaComputerScrollInput.builder()
                    .scrollAmount(0L)
                    .scrollDirection(BetaComputerScrollDirection.UP)
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .text("text")
                    .build()
            )
        assertThat(betaComputerScrollToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerScrollToolUseBlock =
            BetaComputerScrollToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerScrollInput.builder()
                        .scrollAmount(0L)
                        .scrollDirection(BetaComputerScrollDirection.UP)
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerScrollToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerScrollToolUseBlock),
                jacksonTypeRef<BetaComputerScrollToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerScrollToolUseBlock)
            .isEqualTo(betaComputerScrollToolUseBlock)
    }
}
