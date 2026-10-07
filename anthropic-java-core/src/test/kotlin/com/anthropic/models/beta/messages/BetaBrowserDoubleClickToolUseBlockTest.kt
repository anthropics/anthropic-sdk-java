package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserDoubleClickToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserDoubleClickToolUseBlock =
            BetaBrowserDoubleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserDoubleClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserDoubleClickToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserDoubleClickToolUseBlock.input())
            .isEqualTo(
                BetaBrowserDoubleClickInput.builder()
                    .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .modifiers("modifiers")
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserDoubleClickToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserDoubleClickToolUseBlock =
            BetaBrowserDoubleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserDoubleClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserDoubleClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserDoubleClickToolUseBlock),
                jacksonTypeRef<BetaBrowserDoubleClickToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserDoubleClickToolUseBlock)
            .isEqualTo(betaBrowserDoubleClickToolUseBlock)
    }
}
