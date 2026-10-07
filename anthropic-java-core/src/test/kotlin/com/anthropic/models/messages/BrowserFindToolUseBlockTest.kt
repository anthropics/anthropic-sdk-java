package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserFindToolUseBlockTest {

    @Test
    fun create() {
        val browserFindToolUseBlock =
            BrowserFindToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserFindInput.builder().query("query").tabId("tab_id").build())
                .build()

        assertThat(browserFindToolUseBlock.id()).isEqualTo("id")
        assertThat(browserFindToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserFindToolUseBlock.input())
            .isEqualTo(BrowserFindInput.builder().query("query").tabId("tab_id").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserFindToolUseBlock =
            BrowserFindToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserFindInput.builder().query("query").tabId("tab_id").build())
                .build()

        val roundtrippedBrowserFindToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserFindToolUseBlock),
                jacksonTypeRef<BrowserFindToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserFindToolUseBlock).isEqualTo(browserFindToolUseBlock)
    }
}
