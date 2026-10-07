package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerTripleClickToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerTripleClickToolUseBlock =
            BetaComputerTripleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerTripleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerTripleClickToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerTripleClickToolUseBlock.input())
            .isEqualTo(
                BetaComputerTripleClickInput.builder()
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .text("text")
                    .build()
            )
        assertThat(betaComputerTripleClickToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerTripleClickToolUseBlock =
            BetaComputerTripleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerTripleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerTripleClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerTripleClickToolUseBlock),
                jacksonTypeRef<BetaComputerTripleClickToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerTripleClickToolUseBlock)
            .isEqualTo(betaComputerTripleClickToolUseBlock)
    }
}
