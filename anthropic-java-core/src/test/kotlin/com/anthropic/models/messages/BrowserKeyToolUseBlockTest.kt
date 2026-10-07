package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserKeyToolUseBlockTest {

    @Test
    fun create() {
        val browserKeyToolUseBlock =
            BrowserKeyToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserKeyInput.builder().text("text").repeat(1L).tabId("tab_id").build())
                .build()

        assertThat(browserKeyToolUseBlock.id()).isEqualTo("id")
        assertThat(browserKeyToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserKeyToolUseBlock.input())
            .isEqualTo(BrowserKeyInput.builder().text("text").repeat(1L).tabId("tab_id").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserKeyToolUseBlock =
            BrowserKeyToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserKeyInput.builder().text("text").repeat(1L).tabId("tab_id").build())
                .build()

        val roundtrippedBrowserKeyToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserKeyToolUseBlock),
                jacksonTypeRef<BrowserKeyToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserKeyToolUseBlock).isEqualTo(browserKeyToolUseBlock)
    }
}
