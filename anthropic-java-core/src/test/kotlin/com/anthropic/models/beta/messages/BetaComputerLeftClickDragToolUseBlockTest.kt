package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerLeftClickDragToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerLeftClickDragToolUseBlock =
            BetaComputerLeftClickDragToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerLeftClickDragInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .addStartCoordinate(0L)
                        .addStartCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerLeftClickDragToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerLeftClickDragToolUseBlock.input())
            .isEqualTo(
                BetaComputerLeftClickDragInput.builder()
                    .addCoordinate(0L)
                    .addCoordinate(0L)
                    .addStartCoordinate(0L)
                    .addStartCoordinate(0L)
                    .text("text")
                    .build()
            )
        assertThat(betaComputerLeftClickDragToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerLeftClickDragToolUseBlock =
            BetaComputerLeftClickDragToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerLeftClickDragInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .addStartCoordinate(0L)
                        .addStartCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerLeftClickDragToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerLeftClickDragToolUseBlock),
                jacksonTypeRef<BetaComputerLeftClickDragToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerLeftClickDragToolUseBlock)
            .isEqualTo(betaComputerLeftClickDragToolUseBlock)
    }
}
