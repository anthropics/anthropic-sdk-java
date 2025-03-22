// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContentBlockTest {

    @Test
    fun ofText() {
        val text =
            TextBlock.builder()
                .addCitation(
                    CitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .text("text")
                .build()

        val contentBlock = ContentBlock.ofText(text)

        assertThat(contentBlock.text()).contains(text)
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).isEmpty
    }

    @Test
    fun ofToolUse() {
        val toolUse =
            ToolUseBlock.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("x")
                .build()

        val contentBlock = ContentBlock.ofToolUse(toolUse)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.toolUse()).contains(toolUse)
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).isEmpty
    }

    @Test
    fun ofThinking() {
        val thinking = ThinkingBlock.builder().signature("signature").thinking("thinking").build()

        val contentBlock = ContentBlock.ofThinking(thinking)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.thinking()).contains(thinking)
        assertThat(contentBlock.redactedThinking()).isEmpty
    }

    @Test
    fun ofRedactedThinking() {
        val redactedThinking = RedactedThinkingBlock.builder().data("data").build()

        val contentBlock = ContentBlock.ofRedactedThinking(redactedThinking)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).contains(redactedThinking)
    }
}
