package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserNavigateToolUseBlockTest {

    @Test
    fun create() {
        val browserNavigateToolUseBlock =
            BrowserNavigateToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserNavigateInput.builder().url("url").tabId("tab_id").build())
                .build()

        assertThat(browserNavigateToolUseBlock.id()).isEqualTo("id")
        assertThat(browserNavigateToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserNavigateToolUseBlock.input())
            .isEqualTo(BrowserNavigateInput.builder().url("url").tabId("tab_id").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserNavigateToolUseBlock =
            BrowserNavigateToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserNavigateInput.builder().url("url").tabId("tab_id").build())
                .build()

        val roundtrippedBrowserNavigateToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserNavigateToolUseBlock),
                jacksonTypeRef<BrowserNavigateToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserNavigateToolUseBlock).isEqualTo(browserNavigateToolUseBlock)
    }
}
