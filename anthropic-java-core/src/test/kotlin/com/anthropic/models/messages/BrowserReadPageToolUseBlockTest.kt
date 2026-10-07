package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserReadPageToolUseBlockTest {

    @Test
    fun create() {
        val browserReadPageToolUseBlock =
            BrowserReadPageToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserReadPageInput.builder()
                        .depth(1L)
                        .filter(BrowserReadPageFilter.ALL)
                        .ref("ref")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserReadPageToolUseBlock.id()).isEqualTo("id")
        assertThat(browserReadPageToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserReadPageToolUseBlock.input())
            .isEqualTo(
                BrowserReadPageInput.builder()
                    .depth(1L)
                    .filter(BrowserReadPageFilter.ALL)
                    .ref("ref")
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserReadPageToolUseBlock =
            BrowserReadPageToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserReadPageInput.builder()
                        .depth(1L)
                        .filter(BrowserReadPageFilter.ALL)
                        .ref("ref")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserReadPageToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserReadPageToolUseBlock),
                jacksonTypeRef<BrowserReadPageToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserReadPageToolUseBlock).isEqualTo(browserReadPageToolUseBlock)
    }
}
