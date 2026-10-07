package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserScrollToolUseBlockTest {

    @Test
    fun create() {
        val browserScrollToolUseBlock =
            BrowserScrollToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserScrollInput.builder()
                        .scrollDirection(BrowserScrollDirection.UP)
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .scrollAmount(1L)
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserScrollToolUseBlock.id()).isEqualTo("id")
        assertThat(browserScrollToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserScrollToolUseBlock.input())
            .isEqualTo(
                BrowserScrollInput.builder()
                    .scrollDirection(BrowserScrollDirection.UP)
                    .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .scrollAmount(1L)
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserScrollToolUseBlock =
            BrowserScrollToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserScrollInput.builder()
                        .scrollDirection(BrowserScrollDirection.UP)
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .scrollAmount(1L)
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserScrollToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserScrollToolUseBlock),
                jacksonTypeRef<BrowserScrollToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserScrollToolUseBlock).isEqualTo(browserScrollToolUseBlock)
    }
}
