package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserTripleClickToolUseBlockTest {

    @Test
    fun create() {
        val browserTripleClickToolUseBlock =
            BrowserTripleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserTripleClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserTripleClickToolUseBlock.id()).isEqualTo("id")
        assertThat(browserTripleClickToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserTripleClickToolUseBlock.input())
            .isEqualTo(
                BrowserTripleClickInput.builder()
                    .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .modifiers("modifiers")
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserTripleClickToolUseBlock =
            BrowserTripleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserTripleClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserTripleClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserTripleClickToolUseBlock),
                jacksonTypeRef<BrowserTripleClickToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserTripleClickToolUseBlock)
            .isEqualTo(browserTripleClickToolUseBlock)
    }
}
