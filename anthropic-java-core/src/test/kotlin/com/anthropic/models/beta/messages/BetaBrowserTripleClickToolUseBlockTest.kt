package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserTripleClickToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserTripleClickToolUseBlock =
            BetaBrowserTripleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserTripleClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserTripleClickToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserTripleClickToolUseBlock.input())
            .isEqualTo(
                BetaBrowserTripleClickInput.builder()
                    .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .modifiers("modifiers")
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserTripleClickToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserTripleClickToolUseBlock =
            BetaBrowserTripleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserTripleClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserTripleClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserTripleClickToolUseBlock),
                jacksonTypeRef<BetaBrowserTripleClickToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserTripleClickToolUseBlock)
            .isEqualTo(betaBrowserTripleClickToolUseBlock)
    }
}
