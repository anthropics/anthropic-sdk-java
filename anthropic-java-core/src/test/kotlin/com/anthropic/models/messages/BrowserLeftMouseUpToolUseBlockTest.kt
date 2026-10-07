package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserLeftMouseUpToolUseBlockTest {

    @Test
    fun create() {
        val browserLeftMouseUpToolUseBlock =
            BrowserLeftMouseUpToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserLeftMouseUpInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserLeftMouseUpToolUseBlock.id()).isEqualTo("id")
        assertThat(browserLeftMouseUpToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserLeftMouseUpToolUseBlock.input())
            .isEqualTo(
                BrowserLeftMouseUpInput.builder()
                    .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserLeftMouseUpToolUseBlock =
            BrowserLeftMouseUpToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserLeftMouseUpInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserLeftMouseUpToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserLeftMouseUpToolUseBlock),
                jacksonTypeRef<BrowserLeftMouseUpToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserLeftMouseUpToolUseBlock)
            .isEqualTo(browserLeftMouseUpToolUseBlock)
    }
}
