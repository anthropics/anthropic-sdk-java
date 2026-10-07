package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserFormInputToolUseBlockTest {

    @Test
    fun create() {
        val browserFormInputToolUseBlock =
            BrowserFormInputToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserFormInputInput.builder()
                        .target(BrowserRefTarget.of("ref"))
                        .value("string")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserFormInputToolUseBlock.id()).isEqualTo("id")
        assertThat(browserFormInputToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserFormInputToolUseBlock.input())
            .isEqualTo(
                BrowserFormInputInput.builder()
                    .target(BrowserRefTarget.of("ref"))
                    .value("string")
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserFormInputToolUseBlock =
            BrowserFormInputToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserFormInputInput.builder()
                        .target(BrowserRefTarget.of("ref"))
                        .value("string")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserFormInputToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserFormInputToolUseBlock),
                jacksonTypeRef<BrowserFormInputToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserFormInputToolUseBlock).isEqualTo(browserFormInputToolUseBlock)
    }
}
