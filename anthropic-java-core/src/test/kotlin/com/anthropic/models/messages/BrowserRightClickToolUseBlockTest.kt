package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserRightClickToolUseBlockTest {

    @Test
    fun create() {
        val browserRightClickToolUseBlock =
            BrowserRightClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserRightClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserRightClickToolUseBlock.id()).isEqualTo("id")
        assertThat(browserRightClickToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserRightClickToolUseBlock.input())
            .isEqualTo(
                BrowserRightClickInput.builder()
                    .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .modifiers("modifiers")
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserRightClickToolUseBlock =
            BrowserRightClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserRightClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserRightClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserRightClickToolUseBlock),
                jacksonTypeRef<BrowserRightClickToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserRightClickToolUseBlock)
            .isEqualTo(browserRightClickToolUseBlock)
    }
}
