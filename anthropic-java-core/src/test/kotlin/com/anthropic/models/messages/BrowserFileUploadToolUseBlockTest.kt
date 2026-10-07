package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserFileUploadToolUseBlockTest {

    @Test
    fun create() {
        val browserFileUploadToolUseBlock =
            BrowserFileUploadToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserFileUploadInput.builder()
                        .target(BrowserRefTarget.of("ref"))
                        .addDocumentId("string")
                        .addPath("string")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        assertThat(browserFileUploadToolUseBlock.id()).isEqualTo("id")
        assertThat(browserFileUploadToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserFileUploadToolUseBlock.input())
            .isEqualTo(
                BrowserFileUploadInput.builder()
                    .target(BrowserRefTarget.of("ref"))
                    .addDocumentId("string")
                    .addPath("string")
                    .tabId("tab_id")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserFileUploadToolUseBlock =
            BrowserFileUploadToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserFileUploadInput.builder()
                        .target(BrowserRefTarget.of("ref"))
                        .addDocumentId("string")
                        .addPath("string")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val roundtrippedBrowserFileUploadToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserFileUploadToolUseBlock),
                jacksonTypeRef<BrowserFileUploadToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserFileUploadToolUseBlock)
            .isEqualTo(browserFileUploadToolUseBlock)
    }
}
