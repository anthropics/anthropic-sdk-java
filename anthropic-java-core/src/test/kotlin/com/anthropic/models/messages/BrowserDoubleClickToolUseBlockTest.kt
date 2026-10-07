package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserDoubleClickToolUseBlockTest {

    @Test
    fun create() {
        val browserDoubleClickToolUseBlock =
            BrowserDoubleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserDoubleClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserDoubleClickToolUseBlock.id()).isEqualTo("id")
        assertThat(browserDoubleClickToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserDoubleClickToolUseBlock.input())
            .isEqualTo(
                BrowserDoubleClickInput.builder()
                    .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .modifiers("modifiers")
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserDoubleClickToolUseBlock =
            BrowserDoubleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserDoubleClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserDoubleClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserDoubleClickToolUseBlock),
                jacksonTypeRef<BrowserDoubleClickToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserDoubleClickToolUseBlock)
            .isEqualTo(browserDoubleClickToolUseBlock)
    }
}
