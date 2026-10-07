package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserJavascriptExecToolUseBlockTest {

    @Test
    fun create() {
        val browserJavascriptExecToolUseBlock =
            BrowserJavascriptExecToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserJavascriptExecInput.builder().text("text").tabId("tab_id").build())
                .build()

        assertThat(browserJavascriptExecToolUseBlock.id()).isEqualTo("id")
        assertThat(browserJavascriptExecToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserJavascriptExecToolUseBlock.input())
            .isEqualTo(BrowserJavascriptExecInput.builder().text("text").tabId("tab_id").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserJavascriptExecToolUseBlock =
            BrowserJavascriptExecToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserJavascriptExecInput.builder().text("text").tabId("tab_id").build())
                .build()

        val roundtrippedBrowserJavascriptExecToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserJavascriptExecToolUseBlock),
                jacksonTypeRef<BrowserJavascriptExecToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserJavascriptExecToolUseBlock)
            .isEqualTo(browserJavascriptExecToolUseBlock)
    }
}
