package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserWaitToolUseBlockTest {

    @Test
    fun create() {
        val browserWaitToolUseBlock =
            BrowserWaitToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserWaitInput.builder().duration(0.0).tabId("tab_id").build())
                .build()

        assertThat(browserWaitToolUseBlock.id()).isEqualTo("id")
        assertThat(browserWaitToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserWaitToolUseBlock.input())
            .isEqualTo(BrowserWaitInput.builder().duration(0.0).tabId("tab_id").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserWaitToolUseBlock =
            BrowserWaitToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserWaitInput.builder().duration(0.0).tabId("tab_id").build())
                .build()

        val roundtrippedBrowserWaitToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserWaitToolUseBlock),
                jacksonTypeRef<BrowserWaitToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserWaitToolUseBlock).isEqualTo(browserWaitToolUseBlock)
    }
}
