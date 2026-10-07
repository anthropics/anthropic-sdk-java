package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserScrollToToolUseBlockTest {

    @Test
    fun create() {
        val browserScrollToToolUseBlock =
            BrowserScrollToToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserScrollToInput.builder()
                        .target(BrowserRefTarget.of("ref"))
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserScrollToToolUseBlock.id()).isEqualTo("id")
        assertThat(browserScrollToToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserScrollToToolUseBlock.input())
            .isEqualTo(
                BrowserScrollToInput.builder()
                    .target(BrowserRefTarget.of("ref"))
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserScrollToToolUseBlock =
            BrowserScrollToToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserScrollToInput.builder()
                        .target(BrowserRefTarget.of("ref"))
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserScrollToToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserScrollToToolUseBlock),
                jacksonTypeRef<BrowserScrollToToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserScrollToToolUseBlock).isEqualTo(browserScrollToToolUseBlock)
    }
}
