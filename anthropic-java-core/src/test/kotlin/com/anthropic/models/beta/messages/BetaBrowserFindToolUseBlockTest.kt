package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserFindToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserFindToolUseBlock =
            BetaBrowserFindToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserFindInput.builder().query("query").tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserFindToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserFindToolUseBlock.input())
            .isEqualTo(BetaBrowserFindInput.builder().query("query").tabId("tab_id").build())
        assertThat(betaBrowserFindToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserFindToolUseBlock =
            BetaBrowserFindToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserFindInput.builder().query("query").tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserFindToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserFindToolUseBlock),
                jacksonTypeRef<BetaBrowserFindToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserFindToolUseBlock).isEqualTo(betaBrowserFindToolUseBlock)
    }
}
