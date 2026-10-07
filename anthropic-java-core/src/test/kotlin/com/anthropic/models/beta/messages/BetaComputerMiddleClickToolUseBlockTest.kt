package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerMiddleClickToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerMiddleClickToolUseBlock =
            BetaComputerMiddleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerMiddleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerMiddleClickToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerMiddleClickToolUseBlock.input())
            .isEqualTo(
                BetaComputerMiddleClickInput.builder()
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .text("text")
                    .build()
            )
        assertThat(betaComputerMiddleClickToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerMiddleClickToolUseBlock =
            BetaComputerMiddleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerMiddleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerMiddleClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerMiddleClickToolUseBlock),
                jacksonTypeRef<BetaComputerMiddleClickToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerMiddleClickToolUseBlock)
            .isEqualTo(betaComputerMiddleClickToolUseBlock)
    }
}
