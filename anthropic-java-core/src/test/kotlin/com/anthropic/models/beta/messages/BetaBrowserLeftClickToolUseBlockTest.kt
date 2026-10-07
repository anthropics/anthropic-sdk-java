package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserLeftClickToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserLeftClickToolUseBlock =
            BetaBrowserLeftClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserLeftClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserLeftClickToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserLeftClickToolUseBlock.input())
            .isEqualTo(
                BetaBrowserLeftClickInput.builder()
                    .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .modifiers("modifiers")
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserLeftClickToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserLeftClickToolUseBlock =
            BetaBrowserLeftClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserLeftClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserLeftClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserLeftClickToolUseBlock),
                jacksonTypeRef<BetaBrowserLeftClickToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserLeftClickToolUseBlock)
            .isEqualTo(betaBrowserLeftClickToolUseBlock)
    }
}
