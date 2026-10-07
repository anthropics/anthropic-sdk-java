package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerDoubleClickToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerDoubleClickToolUseBlock =
            BetaComputerDoubleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerDoubleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerDoubleClickToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerDoubleClickToolUseBlock.input())
            .isEqualTo(
                BetaComputerDoubleClickInput.builder()
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .text("text")
                    .build()
            )
        assertThat(betaComputerDoubleClickToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerDoubleClickToolUseBlock =
            BetaComputerDoubleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerDoubleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerDoubleClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerDoubleClickToolUseBlock),
                jacksonTypeRef<BetaComputerDoubleClickToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerDoubleClickToolUseBlock)
            .isEqualTo(betaComputerDoubleClickToolUseBlock)
    }
}
