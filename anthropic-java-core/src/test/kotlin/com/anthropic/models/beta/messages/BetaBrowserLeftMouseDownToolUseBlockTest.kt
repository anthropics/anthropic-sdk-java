package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserLeftMouseDownToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserLeftMouseDownToolUseBlock =
            BetaBrowserLeftMouseDownToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserLeftMouseDownInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserLeftMouseDownToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserLeftMouseDownToolUseBlock.input())
            .isEqualTo(
                BetaBrowserLeftMouseDownInput.builder()
                    .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserLeftMouseDownToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserLeftMouseDownToolUseBlock =
            BetaBrowserLeftMouseDownToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserLeftMouseDownInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserLeftMouseDownToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserLeftMouseDownToolUseBlock),
                jacksonTypeRef<BetaBrowserLeftMouseDownToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserLeftMouseDownToolUseBlock)
            .isEqualTo(betaBrowserLeftMouseDownToolUseBlock)
    }
}
