package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserHoverToolUseBlockTest {

    @Test
    fun create() {
        val browserHoverToolUseBlock =
            BrowserHoverToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserHoverInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserHoverToolUseBlock.id()).isEqualTo("id")
        assertThat(browserHoverToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserHoverToolUseBlock.input())
            .isEqualTo(
                BrowserHoverInput.builder()
                    .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserHoverToolUseBlock =
            BrowserHoverToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserHoverInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserHoverToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserHoverToolUseBlock),
                jacksonTypeRef<BrowserHoverToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserHoverToolUseBlock).isEqualTo(browserHoverToolUseBlock)
    }
}
