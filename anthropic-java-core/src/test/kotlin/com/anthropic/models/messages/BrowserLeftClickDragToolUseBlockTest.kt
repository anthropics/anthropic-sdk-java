package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserLeftClickDragToolUseBlockTest {

    @Test
    fun create() {
        val browserLeftClickDragToolUseBlock =
            BrowserLeftClickDragToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserLeftClickDragInput.builder()
                        .from(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserLeftClickDragToolUseBlock.id()).isEqualTo("id")
        assertThat(browserLeftClickDragToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserLeftClickDragToolUseBlock.input())
            .isEqualTo(
                BrowserLeftClickDragInput.builder()
                    .from(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserLeftClickDragToolUseBlock =
            BrowserLeftClickDragToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserLeftClickDragInput.builder()
                        .from(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserLeftClickDragToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserLeftClickDragToolUseBlock),
                jacksonTypeRef<BrowserLeftClickDragToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserLeftClickDragToolUseBlock)
            .isEqualTo(browserLeftClickDragToolUseBlock)
    }
}
