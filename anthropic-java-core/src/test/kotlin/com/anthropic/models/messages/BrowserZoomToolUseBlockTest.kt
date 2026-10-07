package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserZoomToolUseBlockTest {

    @Test
    fun create() {
        val browserZoomToolUseBlock =
            BrowserZoomToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserZoomInput.builder()
                        .region(listOf(0L, 0L, 0L, 0L))
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserZoomToolUseBlock.id()).isEqualTo("id")
        assertThat(browserZoomToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserZoomToolUseBlock.input())
            .isEqualTo(
                BrowserZoomInput.builder().region(listOf(0L, 0L, 0L, 0L)).tabId("tab_id").build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserZoomToolUseBlock =
            BrowserZoomToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserZoomInput.builder()
                        .region(listOf(0L, 0L, 0L, 0L))
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserZoomToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserZoomToolUseBlock),
                jacksonTypeRef<BrowserZoomToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserZoomToolUseBlock).isEqualTo(browserZoomToolUseBlock)
    }
}
