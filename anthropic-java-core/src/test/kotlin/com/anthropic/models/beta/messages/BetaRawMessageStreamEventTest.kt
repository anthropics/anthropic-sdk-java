// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.models.messages.Model
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRawMessageStreamEventTest {

    @Test
    fun ofStart() {
        val start =
            BetaRawMessageStartEvent.builder()
                .message(
                    BetaMessage.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .addContent(
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
                                .text("Hi! My name is Claude.")
                                .build()
                        )
                        .model(Model.CLAUDE_3_7_SONNET_LATEST)
                        .stopReason(BetaStopReason.END_TURN)
                        .stopSequence(null)
                        .usage(
                            BetaUsage.builder()
                                .cacheCreationInputTokens(2051L)
                                .cacheReadInputTokens(2051L)
                                .inputTokens(2095L)
                                .outputTokens(503L)
                                .build()
                        )
                        .build()
                )
                .build()

        val betaRawMessageStreamEvent = BetaRawMessageStreamEvent.ofStart(start)

        assertThat(betaRawMessageStreamEvent.start()).contains(start)
        assertThat(betaRawMessageStreamEvent.delta()).isEmpty
        assertThat(betaRawMessageStreamEvent.stop()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofDelta() {
        val delta =
            BetaRawMessageDeltaEvent.builder()
                .delta(
                    BetaRawMessageDeltaEvent.Delta.builder()
                        .stopReason(BetaStopReason.END_TURN)
                        .stopSequence("stop_sequence")
                        .build()
                )
                .usage(BetaMessageDeltaUsage.builder().outputTokens(503L).build())
                .build()

        val betaRawMessageStreamEvent = BetaRawMessageStreamEvent.ofDelta(delta)

        assertThat(betaRawMessageStreamEvent.start()).isEmpty
        assertThat(betaRawMessageStreamEvent.delta()).contains(delta)
        assertThat(betaRawMessageStreamEvent.stop()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofStop() {
        val stop = BetaRawMessageStopEvent.builder().build()

        val betaRawMessageStreamEvent = BetaRawMessageStreamEvent.ofStop(stop)

        assertThat(betaRawMessageStreamEvent.start()).isEmpty
        assertThat(betaRawMessageStreamEvent.delta()).isEmpty
        assertThat(betaRawMessageStreamEvent.stop()).contains(stop)
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofContentBlockStart() {
        val contentBlockStart =
            BetaRawContentBlockStartEvent.builder()
                .contentBlock(
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
                )
                .index(0L)
                .build()

        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofContentBlockStart(contentBlockStart)

        assertThat(betaRawMessageStreamEvent.start()).isEmpty
        assertThat(betaRawMessageStreamEvent.delta()).isEmpty
        assertThat(betaRawMessageStreamEvent.stop()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).contains(contentBlockStart)
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofContentBlockDelta() {
        val contentBlockDelta =
            BetaRawContentBlockDeltaEvent.builder().betaTextDelta("text").index(0L).build()

        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofContentBlockDelta(contentBlockDelta)

        assertThat(betaRawMessageStreamEvent.start()).isEmpty
        assertThat(betaRawMessageStreamEvent.delta()).isEmpty
        assertThat(betaRawMessageStreamEvent.stop()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).contains(contentBlockDelta)
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).isEmpty
    }

    @Test
    fun ofContentBlockStop() {
        val contentBlockStop = BetaRawContentBlockStopEvent.builder().index(0L).build()

        val betaRawMessageStreamEvent =
            BetaRawMessageStreamEvent.ofContentBlockStop(contentBlockStop)

        assertThat(betaRawMessageStreamEvent.start()).isEmpty
        assertThat(betaRawMessageStreamEvent.delta()).isEmpty
        assertThat(betaRawMessageStreamEvent.stop()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStart()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockDelta()).isEmpty
        assertThat(betaRawMessageStreamEvent.contentBlockStop()).contains(contentBlockStop)
    }
}
