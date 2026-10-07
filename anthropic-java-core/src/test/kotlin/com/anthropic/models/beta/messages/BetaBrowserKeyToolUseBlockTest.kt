package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserKeyToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserKeyToolUseBlock =
            BetaBrowserKeyToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserKeyInput.builder().text("text").repeat(1L).tabId("tab_id").build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserKeyToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserKeyToolUseBlock.input())
            .isEqualTo(
                BetaBrowserKeyInput.builder().text("text").repeat(1L).tabId("tab_id").build()
            )
        assertThat(betaBrowserKeyToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserKeyToolUseBlock =
            BetaBrowserKeyToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserKeyInput.builder().text("text").repeat(1L).tabId("tab_id").build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserKeyToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserKeyToolUseBlock),
                jacksonTypeRef<BetaBrowserKeyToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserKeyToolUseBlock).isEqualTo(betaBrowserKeyToolUseBlock)
    }
}
