package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserReadNetworkToolUseBlockTest {

    @Test
    fun create() {
        val browserReadNetworkToolUseBlock =
            BrowserReadNetworkToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserReadNetworkInput.builder().tabId("tab_id").build())
                .build()

        assertThat(browserReadNetworkToolUseBlock.id()).isEqualTo("id")
        assertThat(browserReadNetworkToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserReadNetworkToolUseBlock.input())
            .isEqualTo(BrowserReadNetworkInput.builder().tabId("tab_id").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserReadNetworkToolUseBlock =
            BrowserReadNetworkToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserReadNetworkInput.builder().tabId("tab_id").build())
                .build()

        val roundtrippedBrowserReadNetworkToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserReadNetworkToolUseBlock),
                jacksonTypeRef<BrowserReadNetworkToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserReadNetworkToolUseBlock)
            .isEqualTo(browserReadNetworkToolUseBlock)
    }
}
