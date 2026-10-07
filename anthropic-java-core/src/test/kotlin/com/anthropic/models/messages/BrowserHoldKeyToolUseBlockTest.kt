package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserHoldKeyToolUseBlockTest {

    @Test
    fun create() {
        val browserHoldKeyToolUseBlock =
            BrowserHoldKeyToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserHoldKeyInput.builder().duration(0.0).text("text").tabId("tab_id").build()
                )
                .build()

        assertThat(browserHoldKeyToolUseBlock.id()).isEqualTo("id")
        assertThat(browserHoldKeyToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserHoldKeyToolUseBlock.input())
            .isEqualTo(
                BrowserHoldKeyInput.builder().duration(0.0).text("text").tabId("tab_id").build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserHoldKeyToolUseBlock =
            BrowserHoldKeyToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserHoldKeyInput.builder().duration(0.0).text("text").tabId("tab_id").build()
                )
                .build()

        val roundtrippedBrowserHoldKeyToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserHoldKeyToolUseBlock),
                jacksonTypeRef<BrowserHoldKeyToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserHoldKeyToolUseBlock).isEqualTo(browserHoldKeyToolUseBlock)
    }
}
