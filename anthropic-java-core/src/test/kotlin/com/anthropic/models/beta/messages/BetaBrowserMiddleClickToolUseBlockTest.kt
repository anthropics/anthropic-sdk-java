package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserMiddleClickToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserMiddleClickToolUseBlock =
            BetaBrowserMiddleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserMiddleClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserMiddleClickToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserMiddleClickToolUseBlock.input())
            .isEqualTo(
                BetaBrowserMiddleClickInput.builder()
                    .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .modifiers("modifiers")
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserMiddleClickToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserMiddleClickToolUseBlock =
            BetaBrowserMiddleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserMiddleClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserMiddleClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserMiddleClickToolUseBlock),
                jacksonTypeRef<BetaBrowserMiddleClickToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserMiddleClickToolUseBlock)
            .isEqualTo(betaBrowserMiddleClickToolUseBlock)
    }
}
