// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import com.anthropic.models.ErrorResponse
import com.anthropic.models.messages.CitationCharLocation
import com.anthropic.models.messages.Message
import com.anthropic.models.messages.Model
import com.anthropic.models.messages.StopReason
import com.anthropic.models.messages.TextBlock
import com.anthropic.models.messages.Usage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageBatchResultTest {

    @Test
    fun ofSucceeded() {
        val succeeded =
            MessageBatchSucceededResult.builder()
                .message(
                    Message.builder()
                        .id("msg_013Zva2CMHLNnXjNJJKqJ2EF")
                        .addContent(
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
                                .text("Hi! My name is Claude.")
                                .build()
                        )
                        .model(Model.CLAUDE_3_7_SONNET_LATEST)
                        .stopReason(StopReason.END_TURN)
                        .stopSequence(null)
                        .usage(
                            Usage.builder()
                                .cacheCreationInputTokens(2051L)
                                .cacheReadInputTokens(2051L)
                                .inputTokens(2095L)
                                .outputTokens(503L)
                                .build()
                        )
                        .build()
                )
                .build()

        val messageBatchResult = MessageBatchResult.ofSucceeded(succeeded)

        assertThat(messageBatchResult.succeeded()).contains(succeeded)
        assertThat(messageBatchResult.errored()).isEmpty
        assertThat(messageBatchResult.canceled()).isEmpty
        assertThat(messageBatchResult.expired()).isEmpty
    }

    @Test
    fun ofErrored() {
        val errored =
            MessageBatchErroredResult.builder()
                .error(ErrorResponse.builder().invalidRequestErrorError("message").build())
                .build()

        val messageBatchResult = MessageBatchResult.ofErrored(errored)

        assertThat(messageBatchResult.succeeded()).isEmpty
        assertThat(messageBatchResult.errored()).contains(errored)
        assertThat(messageBatchResult.canceled()).isEmpty
        assertThat(messageBatchResult.expired()).isEmpty
    }

    @Test
    fun ofCanceled() {
        val canceled = MessageBatchCanceledResult.builder().build()

        val messageBatchResult = MessageBatchResult.ofCanceled(canceled)

        assertThat(messageBatchResult.succeeded()).isEmpty
        assertThat(messageBatchResult.errored()).isEmpty
        assertThat(messageBatchResult.canceled()).contains(canceled)
        assertThat(messageBatchResult.expired()).isEmpty
    }

    @Test
    fun ofExpired() {
        val expired = MessageBatchExpiredResult.builder().build()

        val messageBatchResult = MessageBatchResult.ofExpired(expired)

        assertThat(messageBatchResult.succeeded()).isEmpty
        assertThat(messageBatchResult.errored()).isEmpty
        assertThat(messageBatchResult.canceled()).isEmpty
        assertThat(messageBatchResult.expired()).contains(expired)
    }
}
