// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageBatchRequestCountsTest {

    @Test
    fun create() {
        val messageBatchRequestCounts =
            MessageBatchRequestCounts.builder()
                .canceled(10L)
                .errored(30L)
                .expired(10L)
                .processing(100L)
                .succeeded(50L)
                .build()

        assertThat(messageBatchRequestCounts.canceled()).isEqualTo(10L)
        assertThat(messageBatchRequestCounts.errored()).isEqualTo(30L)
        assertThat(messageBatchRequestCounts.expired()).isEqualTo(10L)
        assertThat(messageBatchRequestCounts.processing()).isEqualTo(100L)
        assertThat(messageBatchRequestCounts.succeeded()).isEqualTo(50L)
    }
}
