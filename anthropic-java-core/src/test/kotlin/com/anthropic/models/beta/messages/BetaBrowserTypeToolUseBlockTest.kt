package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserTypeToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserTypeToolUseBlock =
            BetaBrowserTypeToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserTypeInput.builder().text("text").tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserTypeToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserTypeToolUseBlock.input())
            .isEqualTo(BetaBrowserTypeInput.builder().text("text").tabId("tab_id").build())
        assertThat(betaBrowserTypeToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserTypeToolUseBlock =
            BetaBrowserTypeToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserTypeInput.builder().text("text").tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserTypeToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserTypeToolUseBlock),
                jacksonTypeRef<BetaBrowserTypeToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserTypeToolUseBlock).isEqualTo(betaBrowserTypeToolUseBlock)
    }
}
