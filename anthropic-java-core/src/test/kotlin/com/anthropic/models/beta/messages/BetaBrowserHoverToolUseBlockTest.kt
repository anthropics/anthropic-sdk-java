package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserHoverToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserHoverToolUseBlock =
            BetaBrowserHoverToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserHoverInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserHoverToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserHoverToolUseBlock.input())
            .isEqualTo(
                BetaBrowserHoverInput.builder()
                    .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserHoverToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserHoverToolUseBlock =
            BetaBrowserHoverToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserHoverInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserHoverToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserHoverToolUseBlock),
                jacksonTypeRef<BetaBrowserHoverToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserHoverToolUseBlock).isEqualTo(betaBrowserHoverToolUseBlock)
    }
}
