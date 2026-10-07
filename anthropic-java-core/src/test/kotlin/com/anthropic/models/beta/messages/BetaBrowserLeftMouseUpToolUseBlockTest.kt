package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserLeftMouseUpToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserLeftMouseUpToolUseBlock =
            BetaBrowserLeftMouseUpToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserLeftMouseUpInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserLeftMouseUpToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserLeftMouseUpToolUseBlock.input())
            .isEqualTo(
                BetaBrowserLeftMouseUpInput.builder()
                    .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserLeftMouseUpToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserLeftMouseUpToolUseBlock =
            BetaBrowserLeftMouseUpToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserLeftMouseUpInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserLeftMouseUpToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserLeftMouseUpToolUseBlock),
                jacksonTypeRef<BetaBrowserLeftMouseUpToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserLeftMouseUpToolUseBlock)
            .isEqualTo(betaBrowserLeftMouseUpToolUseBlock)
    }
}
