// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RawContentBlockDeltaTest {

    @Test
    fun ofText() {
        val text = TextDelta.builder().text("text").build()

        val rawContentBlockDelta = RawContentBlockDelta.ofText(text)

        assertThat(rawContentBlockDelta.text()).contains(text)
        assertThat(rawContentBlockDelta.inputJson()).isEmpty
        assertThat(rawContentBlockDelta.citations()).isEmpty
        assertThat(rawContentBlockDelta.thinking()).isEmpty
        assertThat(rawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofInputJson() {
        val inputJson = InputJsonDelta.builder().partialJson("partial_json").build()

        val rawContentBlockDelta = RawContentBlockDelta.ofInputJson(inputJson)

        assertThat(rawContentBlockDelta.text()).isEmpty
        assertThat(rawContentBlockDelta.inputJson()).contains(inputJson)
        assertThat(rawContentBlockDelta.citations()).isEmpty
        assertThat(rawContentBlockDelta.thinking()).isEmpty
        assertThat(rawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofCitations() {
        val citations =
            CitationsDelta.builder()
                .citation(
                    CitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val rawContentBlockDelta = RawContentBlockDelta.ofCitations(citations)

        assertThat(rawContentBlockDelta.text()).isEmpty
        assertThat(rawContentBlockDelta.inputJson()).isEmpty
        assertThat(rawContentBlockDelta.citations()).contains(citations)
        assertThat(rawContentBlockDelta.thinking()).isEmpty
        assertThat(rawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofThinking() {
        val thinking = ThinkingDelta.builder().thinking("thinking").build()

        val rawContentBlockDelta = RawContentBlockDelta.ofThinking(thinking)

        assertThat(rawContentBlockDelta.text()).isEmpty
        assertThat(rawContentBlockDelta.inputJson()).isEmpty
        assertThat(rawContentBlockDelta.citations()).isEmpty
        assertThat(rawContentBlockDelta.thinking()).contains(thinking)
        assertThat(rawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofSignature() {
        val signature = SignatureDelta.builder().signature("signature").build()

        val rawContentBlockDelta = RawContentBlockDelta.ofSignature(signature)

        assertThat(rawContentBlockDelta.text()).isEmpty
        assertThat(rawContentBlockDelta.inputJson()).isEmpty
        assertThat(rawContentBlockDelta.citations()).isEmpty
        assertThat(rawContentBlockDelta.thinking()).isEmpty
        assertThat(rawContentBlockDelta.signature()).contains(signature)
    }
}
