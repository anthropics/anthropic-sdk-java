package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserReadConsoleToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserReadConsoleToolUseBlock =
            BetaBrowserReadConsoleToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserReadConsoleInput.builder().tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserReadConsoleToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserReadConsoleToolUseBlock.input())
            .isEqualTo(BetaBrowserReadConsoleInput.builder().tabId("tab_id").build())
        assertThat(betaBrowserReadConsoleToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserReadConsoleToolUseBlock =
            BetaBrowserReadConsoleToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserReadConsoleInput.builder().tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserReadConsoleToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserReadConsoleToolUseBlock),
                jacksonTypeRef<BetaBrowserReadConsoleToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserReadConsoleToolUseBlock)
            .isEqualTo(betaBrowserReadConsoleToolUseBlock)
    }
}
