package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserMouseMoveToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserMouseMoveToolUseBlock =
            BetaBrowserMouseMoveToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserMouseMoveInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserMouseMoveToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserMouseMoveToolUseBlock.input())
            .isEqualTo(
                BetaBrowserMouseMoveInput.builder()
                    .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserMouseMoveToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserMouseMoveToolUseBlock =
            BetaBrowserMouseMoveToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserMouseMoveInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserMouseMoveToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserMouseMoveToolUseBlock),
                jacksonTypeRef<BetaBrowserMouseMoveToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserMouseMoveToolUseBlock)
            .isEqualTo(betaBrowserMouseMoveToolUseBlock)
    }
}
