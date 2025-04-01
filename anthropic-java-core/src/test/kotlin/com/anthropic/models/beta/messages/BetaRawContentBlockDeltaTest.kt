// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRawContentBlockDeltaTest {

    @Test
    fun ofText() {
        val text = BetaTextDelta.builder().text("text").build()

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofText(text)

        assertThat(betaRawContentBlockDelta.text()).contains(text)
        assertThat(betaRawContentBlockDelta.inputJson()).isEmpty
        assertThat(betaRawContentBlockDelta.citations()).isEmpty
        assertThat(betaRawContentBlockDelta.thinking()).isEmpty
        assertThat(betaRawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofInputJson() {
        val inputJson = BetaInputJsonDelta.builder().partialJson("partial_json").build()

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofInputJson(inputJson)

        assertThat(betaRawContentBlockDelta.text()).isEmpty
        assertThat(betaRawContentBlockDelta.inputJson()).contains(inputJson)
        assertThat(betaRawContentBlockDelta.citations()).isEmpty
        assertThat(betaRawContentBlockDelta.thinking()).isEmpty
        assertThat(betaRawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofCitations() {
        val citations =
            BetaCitationsDelta.builder()
                .citation(
                    BetaCitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofCitations(citations)

        assertThat(betaRawContentBlockDelta.text()).isEmpty
        assertThat(betaRawContentBlockDelta.inputJson()).isEmpty
        assertThat(betaRawContentBlockDelta.citations()).contains(citations)
        assertThat(betaRawContentBlockDelta.thinking()).isEmpty
        assertThat(betaRawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofThinking() {
        val thinking = BetaThinkingDelta.builder().thinking("thinking").build()

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofThinking(thinking)

        assertThat(betaRawContentBlockDelta.text()).isEmpty
        assertThat(betaRawContentBlockDelta.inputJson()).isEmpty
        assertThat(betaRawContentBlockDelta.citations()).isEmpty
        assertThat(betaRawContentBlockDelta.thinking()).contains(thinking)
        assertThat(betaRawContentBlockDelta.signature()).isEmpty
    }

    @Test
    fun ofSignature() {
        val signature = BetaSignatureDelta.builder().signature("signature").build()

        val betaRawContentBlockDelta = BetaRawContentBlockDelta.ofSignature(signature)

        assertThat(betaRawContentBlockDelta.text()).isEmpty
        assertThat(betaRawContentBlockDelta.inputJson()).isEmpty
        assertThat(betaRawContentBlockDelta.citations()).isEmpty
        assertThat(betaRawContentBlockDelta.thinking()).isEmpty
        assertThat(betaRawContentBlockDelta.signature()).contains(signature)
    }
}
