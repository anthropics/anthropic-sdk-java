// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageBatchCanceledResultTest {

    @Test
    fun createMessageBatchCanceledResult() {
        val messageBatchCanceledResult = MessageBatchCanceledResult.builder().build()
        assertThat(messageBatchCanceledResult).isNotNull
    }
}
