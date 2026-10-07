package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserReadNetworkToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserReadNetworkToolUseBlock =
            BetaBrowserReadNetworkToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserReadNetworkInput.builder().tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserReadNetworkToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserReadNetworkToolUseBlock.input())
            .isEqualTo(BetaBrowserReadNetworkInput.builder().tabId("tab_id").build())
        assertThat(betaBrowserReadNetworkToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserReadNetworkToolUseBlock =
            BetaBrowserReadNetworkToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserReadNetworkInput.builder().tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserReadNetworkToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserReadNetworkToolUseBlock),
                jacksonTypeRef<BetaBrowserReadNetworkToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserReadNetworkToolUseBlock)
            .isEqualTo(betaBrowserReadNetworkToolUseBlock)
    }
}
