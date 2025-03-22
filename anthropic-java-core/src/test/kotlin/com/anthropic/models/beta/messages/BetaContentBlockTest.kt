// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContentBlockTest {

    @Test
    fun ofText() {
        val text =
            BetaTextBlock.builder()
                .addCitation(
                    BetaCitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .text("text")
                .build()

        val betaContentBlock = BetaContentBlock.ofText(text)

        assertThat(betaContentBlock.text()).contains(text)
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
    }

    @Test
    fun ofToolUse() {
        val toolUse =
            BetaToolUseBlock.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("x")
                .build()

        val betaContentBlock = BetaContentBlock.ofToolUse(toolUse)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.toolUse()).contains(toolUse)
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
    }

    @Test
    fun ofThinking() {
        val thinking =
            BetaThinkingBlock.builder().signature("signature").thinking("thinking").build()

        val betaContentBlock = BetaContentBlock.ofThinking(thinking)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.thinking()).contains(thinking)
        assertThat(betaContentBlock.redactedThinking()).isEmpty
    }

    @Test
    fun ofRedactedThinking() {
        val redactedThinking = BetaRedactedThinkingBlock.builder().data("data").build()

        val betaContentBlock = BetaContentBlock.ofRedactedThinking(redactedThinking)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).contains(redactedThinking)
    }
}
