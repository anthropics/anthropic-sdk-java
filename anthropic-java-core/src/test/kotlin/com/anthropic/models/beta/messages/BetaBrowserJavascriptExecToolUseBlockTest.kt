package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserJavascriptExecToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserJavascriptExecToolUseBlock =
            BetaBrowserJavascriptExecToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserJavascriptExecInput.builder().text("text").tabId("tab_id").build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserJavascriptExecToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserJavascriptExecToolUseBlock.input())
            .isEqualTo(
                BetaBrowserJavascriptExecInput.builder().text("text").tabId("tab_id").build()
            )
        assertThat(betaBrowserJavascriptExecToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserJavascriptExecToolUseBlock =
            BetaBrowserJavascriptExecToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserJavascriptExecInput.builder().text("text").tabId("tab_id").build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserJavascriptExecToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserJavascriptExecToolUseBlock),
                jacksonTypeRef<BetaBrowserJavascriptExecToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserJavascriptExecToolUseBlock)
            .isEqualTo(betaBrowserJavascriptExecToolUseBlock)
    }
}
