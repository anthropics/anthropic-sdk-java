package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserScrollToToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserScrollToToolUseBlock =
            BetaBrowserScrollToToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserScrollToInput.builder()
                        .target(BetaBrowserRefTarget.of("ref"))
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserScrollToToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserScrollToToolUseBlock.input())
            .isEqualTo(
                BetaBrowserScrollToInput.builder()
                    .target(BetaBrowserRefTarget.of("ref"))
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserScrollToToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserScrollToToolUseBlock =
            BetaBrowserScrollToToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserScrollToInput.builder()
                        .target(BetaBrowserRefTarget.of("ref"))
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserScrollToToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserScrollToToolUseBlock),
                jacksonTypeRef<BetaBrowserScrollToToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserScrollToToolUseBlock)
            .isEqualTo(betaBrowserScrollToToolUseBlock)
    }
}
