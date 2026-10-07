package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserWaitToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserWaitToolUseBlock =
            BetaBrowserWaitToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserWaitInput.builder().duration(0.0).tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserWaitToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserWaitToolUseBlock.input())
            .isEqualTo(BetaBrowserWaitInput.builder().duration(0.0).tabId("tab_id").build())
        assertThat(betaBrowserWaitToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserWaitToolUseBlock =
            BetaBrowserWaitToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserWaitInput.builder().duration(0.0).tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserWaitToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserWaitToolUseBlock),
                jacksonTypeRef<BetaBrowserWaitToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserWaitToolUseBlock).isEqualTo(betaBrowserWaitToolUseBlock)
    }
}
