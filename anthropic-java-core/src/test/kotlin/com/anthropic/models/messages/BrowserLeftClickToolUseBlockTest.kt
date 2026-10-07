package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserLeftClickToolUseBlockTest {

    @Test
    fun create() {
        val browserLeftClickToolUseBlock =
            BrowserLeftClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserLeftClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserLeftClickToolUseBlock.id()).isEqualTo("id")
        assertThat(browserLeftClickToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserLeftClickToolUseBlock.input())
            .isEqualTo(
                BrowserLeftClickInput.builder()
                    .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .modifiers("modifiers")
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserLeftClickToolUseBlock =
            BrowserLeftClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserLeftClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserLeftClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserLeftClickToolUseBlock),
                jacksonTypeRef<BrowserLeftClickToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserLeftClickToolUseBlock).isEqualTo(browserLeftClickToolUseBlock)
    }
}
