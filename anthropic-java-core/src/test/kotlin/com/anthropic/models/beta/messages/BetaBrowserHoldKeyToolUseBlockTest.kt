package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserHoldKeyToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserHoldKeyToolUseBlock =
            BetaBrowserHoldKeyToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserHoldKeyInput.builder()
                        .duration(0.0)
                        .text("text")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserHoldKeyToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserHoldKeyToolUseBlock.input())
            .isEqualTo(
                BetaBrowserHoldKeyInput.builder().duration(0.0).text("text").tabId("tab_id").build()
            )
        assertThat(betaBrowserHoldKeyToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserHoldKeyToolUseBlock =
            BetaBrowserHoldKeyToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserHoldKeyInput.builder()
                        .duration(0.0)
                        .text("text")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserHoldKeyToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserHoldKeyToolUseBlock),
                jacksonTypeRef<BetaBrowserHoldKeyToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserHoldKeyToolUseBlock)
            .isEqualTo(betaBrowserHoldKeyToolUseBlock)
    }
}
