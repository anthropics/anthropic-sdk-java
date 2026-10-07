package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerZoomToolUseBlockTest {

    @Test
    fun create() {
        val betaComputerZoomToolUseBlock =
            BetaComputerZoomToolUseBlock.builder()
                .id("id")
                .input(BetaComputerZoomInput.of(listOf(0L, 0L, 0L, 0L)))
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaComputerZoomToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerZoomToolUseBlock.input())
            .isEqualTo(BetaComputerZoomInput.of(listOf(0L, 0L, 0L, 0L)))
        assertThat(betaComputerZoomToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerZoomToolUseBlock =
            BetaComputerZoomToolUseBlock.builder()
                .id("id")
                .input(BetaComputerZoomInput.of(listOf(0L, 0L, 0L, 0L)))
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaComputerZoomToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerZoomToolUseBlock),
                jacksonTypeRef<BetaComputerZoomToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerZoomToolUseBlock).isEqualTo(betaComputerZoomToolUseBlock)
    }
}
