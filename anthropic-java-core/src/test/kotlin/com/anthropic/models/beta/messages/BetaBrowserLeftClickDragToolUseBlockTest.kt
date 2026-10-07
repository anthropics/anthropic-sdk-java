package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserLeftClickDragToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserLeftClickDragToolUseBlock =
            BetaBrowserLeftClickDragToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserLeftClickDragInput.builder()
                        .from(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserLeftClickDragToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserLeftClickDragToolUseBlock.input())
            .isEqualTo(
                BetaBrowserLeftClickDragInput.builder()
                    .from(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserLeftClickDragToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserLeftClickDragToolUseBlock =
            BetaBrowserLeftClickDragToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserLeftClickDragInput.builder()
                        .from(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserLeftClickDragToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserLeftClickDragToolUseBlock),
                jacksonTypeRef<BetaBrowserLeftClickDragToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserLeftClickDragToolUseBlock)
            .isEqualTo(betaBrowserLeftClickDragToolUseBlock)
    }
}
