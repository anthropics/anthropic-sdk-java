package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserLeftMouseDownToolUseBlockTest {

    @Test
    fun create() {
        val browserLeftMouseDownToolUseBlock =
            BrowserLeftMouseDownToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserLeftMouseDownInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserLeftMouseDownToolUseBlock.id()).isEqualTo("id")
        assertThat(browserLeftMouseDownToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserLeftMouseDownToolUseBlock.input())
            .isEqualTo(
                BrowserLeftMouseDownInput.builder()
                    .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserLeftMouseDownToolUseBlock =
            BrowserLeftMouseDownToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserLeftMouseDownInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserLeftMouseDownToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserLeftMouseDownToolUseBlock),
                jacksonTypeRef<BrowserLeftMouseDownToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserLeftMouseDownToolUseBlock)
            .isEqualTo(browserLeftMouseDownToolUseBlock)
    }
}
