package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserMiddleClickToolUseBlockTest {

    @Test
    fun create() {
        val browserMiddleClickToolUseBlock =
            BrowserMiddleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserMiddleClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserMiddleClickToolUseBlock.id()).isEqualTo("id")
        assertThat(browserMiddleClickToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserMiddleClickToolUseBlock.input())
            .isEqualTo(
                BrowserMiddleClickInput.builder()
                    .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .modifiers("modifiers")
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserMiddleClickToolUseBlock =
            BrowserMiddleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserMiddleClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserMiddleClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserMiddleClickToolUseBlock),
                jacksonTypeRef<BrowserMiddleClickToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserMiddleClickToolUseBlock)
            .isEqualTo(browserMiddleClickToolUseBlock)
    }
}
