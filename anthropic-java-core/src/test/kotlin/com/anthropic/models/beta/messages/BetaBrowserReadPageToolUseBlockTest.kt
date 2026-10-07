package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserReadPageToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserReadPageToolUseBlock =
            BetaBrowserReadPageToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserReadPageInput.builder()
                        .depth(1L)
                        .filter(BetaBrowserReadPageFilter.ALL)
                        .ref("ref")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserReadPageToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserReadPageToolUseBlock.input())
            .isEqualTo(
                BetaBrowserReadPageInput.builder()
                    .depth(1L)
                    .filter(BetaBrowserReadPageFilter.ALL)
                    .ref("ref")
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserReadPageToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserReadPageToolUseBlock =
            BetaBrowserReadPageToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserReadPageInput.builder()
                        .depth(1L)
                        .filter(BetaBrowserReadPageFilter.ALL)
                        .ref("ref")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserReadPageToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserReadPageToolUseBlock),
                jacksonTypeRef<BetaBrowserReadPageToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserReadPageToolUseBlock)
            .isEqualTo(betaBrowserReadPageToolUseBlock)
    }
}
