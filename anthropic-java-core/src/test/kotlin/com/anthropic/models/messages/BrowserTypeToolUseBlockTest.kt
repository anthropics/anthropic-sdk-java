package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserTypeToolUseBlockTest {

    @Test
    fun create() {
        val browserTypeToolUseBlock =
            BrowserTypeToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserTypeInput.builder().text("text").tabId("tab_id").build())
                .build()

        assertThat(browserTypeToolUseBlock.id()).isEqualTo("id")
        assertThat(browserTypeToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserTypeToolUseBlock.input())
            .isEqualTo(BrowserTypeInput.builder().text("text").tabId("tab_id").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserTypeToolUseBlock =
            BrowserTypeToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserTypeInput.builder().text("text").tabId("tab_id").build())
                .build()

        val roundtrippedBrowserTypeToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserTypeToolUseBlock),
                jacksonTypeRef<BrowserTypeToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserTypeToolUseBlock).isEqualTo(browserTypeToolUseBlock)
    }
}
