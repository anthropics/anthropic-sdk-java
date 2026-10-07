package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserScrollToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserScrollToolUseBlock =
            BetaBrowserScrollToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserScrollInput.builder()
                        .scrollDirection(BetaBrowserScrollDirection.UP)
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .scrollAmount(1L)
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserScrollToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserScrollToolUseBlock.input())
            .isEqualTo(
                BetaBrowserScrollInput.builder()
                    .scrollDirection(BetaBrowserScrollDirection.UP)
                    .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .scrollAmount(1L)
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserScrollToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserScrollToolUseBlock =
            BetaBrowserScrollToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserScrollInput.builder()
                        .scrollDirection(BetaBrowserScrollDirection.UP)
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .scrollAmount(1L)
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserScrollToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserScrollToolUseBlock),
                jacksonTypeRef<BetaBrowserScrollToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserScrollToolUseBlock)
            .isEqualTo(betaBrowserScrollToolUseBlock)
    }
}
