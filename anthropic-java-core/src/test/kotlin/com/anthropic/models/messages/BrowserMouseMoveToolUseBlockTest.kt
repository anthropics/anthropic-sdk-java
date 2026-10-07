package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserMouseMoveToolUseBlockTest {

    @Test
    fun create() {
        val browserMouseMoveToolUseBlock =
            BrowserMouseMoveToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserMouseMoveInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserMouseMoveToolUseBlock.id()).isEqualTo("id")
        assertThat(browserMouseMoveToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserMouseMoveToolUseBlock.input())
            .isEqualTo(
                BrowserMouseMoveInput.builder()
                    .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserMouseMoveToolUseBlock =
            BrowserMouseMoveToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserMouseMoveInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserMouseMoveToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserMouseMoveToolUseBlock),
                jacksonTypeRef<BrowserMouseMoveToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserMouseMoveToolUseBlock).isEqualTo(browserMouseMoveToolUseBlock)
    }
}
