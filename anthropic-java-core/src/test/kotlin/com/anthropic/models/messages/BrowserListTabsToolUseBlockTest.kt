package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserListTabsToolUseBlockTest {

    @Test
    fun create() {
        val browserListTabsToolUseBlock =
            BrowserListTabsToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserListTabsInput.builder().build())
                .build()

        assertThat(browserListTabsToolUseBlock.id()).isEqualTo("id")
        assertThat(browserListTabsToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserListTabsToolUseBlock.input())
            .isEqualTo(BrowserListTabsInput.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserListTabsToolUseBlock =
            BrowserListTabsToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserListTabsInput.builder().build())
                .build()

        val roundtrippedBrowserListTabsToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserListTabsToolUseBlock),
                jacksonTypeRef<BrowserListTabsToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserListTabsToolUseBlock).isEqualTo(browserListTabsToolUseBlock)
    }
}
