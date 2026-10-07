package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserReadConsoleToolUseBlockTest {

    @Test
    fun create() {
        val browserReadConsoleToolUseBlock =
            BrowserReadConsoleToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserReadConsoleInput.builder().tabId("tab_id").build())
                .build()

        assertThat(browserReadConsoleToolUseBlock.id()).isEqualTo("id")
        assertThat(browserReadConsoleToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserReadConsoleToolUseBlock.input())
            .isEqualTo(BrowserReadConsoleInput.builder().tabId("tab_id").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserReadConsoleToolUseBlock =
            BrowserReadConsoleToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserReadConsoleInput.builder().tabId("tab_id").build())
                .build()

        val roundtrippedBrowserReadConsoleToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserReadConsoleToolUseBlock),
                jacksonTypeRef<BrowserReadConsoleToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserReadConsoleToolUseBlock)
            .isEqualTo(browserReadConsoleToolUseBlock)
    }
}
