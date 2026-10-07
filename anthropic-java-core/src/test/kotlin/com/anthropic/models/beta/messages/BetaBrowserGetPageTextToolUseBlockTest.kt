package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserGetPageTextToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserGetPageTextToolUseBlock =
            BetaBrowserGetPageTextToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserGetPageTextInput.builder().tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserGetPageTextToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserGetPageTextToolUseBlock.input())
            .isEqualTo(BetaBrowserGetPageTextInput.builder().tabId("tab_id").build())
        assertThat(betaBrowserGetPageTextToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserGetPageTextToolUseBlock =
            BetaBrowserGetPageTextToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserGetPageTextInput.builder().tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserGetPageTextToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserGetPageTextToolUseBlock),
                jacksonTypeRef<BetaBrowserGetPageTextToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserGetPageTextToolUseBlock)
            .isEqualTo(betaBrowserGetPageTextToolUseBlock)
    }
}
