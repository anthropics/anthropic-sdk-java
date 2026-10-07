package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserScreenshotToolUseBlockTest {

    @Test
    fun create() {
        val browserScreenshotToolUseBlock =
            BrowserScreenshotToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserScreenshotInput.builder().tabId("tab_id").build())
                .build()

        assertThat(browserScreenshotToolUseBlock.id()).isEqualTo("id")
        assertThat(browserScreenshotToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserScreenshotToolUseBlock.input())
            .isEqualTo(BrowserScreenshotInput.builder().tabId("tab_id").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserScreenshotToolUseBlock =
            BrowserScreenshotToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserScreenshotInput.builder().tabId("tab_id").build())
                .build()

        val roundtrippedBrowserScreenshotToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserScreenshotToolUseBlock),
                jacksonTypeRef<BrowserScreenshotToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserScreenshotToolUseBlock)
            .isEqualTo(browserScreenshotToolUseBlock)
    }
}
