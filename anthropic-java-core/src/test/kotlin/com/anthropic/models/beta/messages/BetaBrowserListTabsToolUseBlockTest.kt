package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserListTabsToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserListTabsToolUseBlock =
            BetaBrowserListTabsToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserListTabsInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserListTabsToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserListTabsToolUseBlock.input())
            .isEqualTo(BetaBrowserListTabsInput.builder().build())
        assertThat(betaBrowserListTabsToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserListTabsToolUseBlock =
            BetaBrowserListTabsToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserListTabsInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserListTabsToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserListTabsToolUseBlock),
                jacksonTypeRef<BetaBrowserListTabsToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserListTabsToolUseBlock)
            .isEqualTo(betaBrowserListTabsToolUseBlock)
    }
}
