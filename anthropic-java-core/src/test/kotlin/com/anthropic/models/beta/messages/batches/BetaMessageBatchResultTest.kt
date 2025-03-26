// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.models.beta.BetaErrorResponse
import com.anthropic.models.beta.messages.BetaCitationCharLocation
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaStopReason
import com.anthropic.models.beta.messages.BetaTextBlock
import com.anthropic.models.beta.messages.BetaUsage
import com.anthropic.models.messages.Model
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageBatchResultTest {

    @Test
    fun ofSucceeded() {
        val succeeded =
            BetaMessageBatchSucceededResult.builder()
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

        val betaMessageBatchResult = BetaMessageBatchResult.ofSucceeded(succeeded)

        assertThat(betaMessageBatchResult.succeeded()).contains(succeeded)
        assertThat(betaMessageBatchResult.errored()).isEmpty
        assertThat(betaMessageBatchResult.canceled()).isEmpty
        assertThat(betaMessageBatchResult.expired()).isEmpty
    }

    @Test
    fun ofErrored() {
        val errored =
            BetaMessageBatchErroredResult.builder()
                .error(BetaErrorResponse.builder().invalidRequestError("message").build())
                .build()

        val betaMessageBatchResult = BetaMessageBatchResult.ofErrored(errored)

        assertThat(betaMessageBatchResult.succeeded()).isEmpty
        assertThat(betaMessageBatchResult.errored()).contains(errored)
        assertThat(betaMessageBatchResult.canceled()).isEmpty
        assertThat(betaMessageBatchResult.expired()).isEmpty
    }

    @Test
    fun ofCanceled() {
        val canceled = BetaMessageBatchCanceledResult.builder().build()

        val betaMessageBatchResult = BetaMessageBatchResult.ofCanceled(canceled)

        assertThat(betaMessageBatchResult.succeeded()).isEmpty
        assertThat(betaMessageBatchResult.errored()).isEmpty
        assertThat(betaMessageBatchResult.canceled()).contains(canceled)
        assertThat(betaMessageBatchResult.expired()).isEmpty
    }

    @Test
    fun ofExpired() {
        val expired = BetaMessageBatchExpiredResult.builder().build()

        val betaMessageBatchResult = BetaMessageBatchResult.ofExpired(expired)

        assertThat(betaMessageBatchResult.succeeded()).isEmpty
        assertThat(betaMessageBatchResult.errored()).isEmpty
        assertThat(betaMessageBatchResult.canceled()).isEmpty
        assertThat(betaMessageBatchResult.expired()).contains(expired)
    }
}
