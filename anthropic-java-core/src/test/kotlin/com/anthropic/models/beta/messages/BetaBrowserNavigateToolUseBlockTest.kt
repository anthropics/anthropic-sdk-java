package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserNavigateToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserNavigateToolUseBlock =
            BetaBrowserNavigateToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserNavigateInput.builder().url("url").tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserNavigateToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserNavigateToolUseBlock.input())
            .isEqualTo(BetaBrowserNavigateInput.builder().url("url").tabId("tab_id").build())
        assertThat(betaBrowserNavigateToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserNavigateToolUseBlock =
            BetaBrowserNavigateToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserNavigateInput.builder().url("url").tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserNavigateToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserNavigateToolUseBlock),
                jacksonTypeRef<BetaBrowserNavigateToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserNavigateToolUseBlock)
            .isEqualTo(betaBrowserNavigateToolUseBlock)
    }
}
