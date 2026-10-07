package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserGetPageTextToolUseBlockTest {

    @Test
    fun create() {
        val browserGetPageTextToolUseBlock =
            BrowserGetPageTextToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserGetPageTextInput.builder().tabId("tab_id").build())
                .build()

        assertThat(browserGetPageTextToolUseBlock.id()).isEqualTo("id")
        assertThat(browserGetPageTextToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserGetPageTextToolUseBlock.input())
            .isEqualTo(BrowserGetPageTextInput.builder().tabId("tab_id").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserGetPageTextToolUseBlock =
            BrowserGetPageTextToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserGetPageTextInput.builder().tabId("tab_id").build())
                .build()

        val roundtrippedBrowserGetPageTextToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserGetPageTextToolUseBlock),
                jacksonTypeRef<BrowserGetPageTextToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserGetPageTextToolUseBlock)
            .isEqualTo(browserGetPageTextToolUseBlock)
    }
}
