// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageBatchCanceledResultTest {

    @Test
    fun createMessageBatchCanceledResult() {
        val messageBatchCanceledResult =
            MessageBatchCanceledResult.builder()
                .type(MessageBatchCanceledResult.Type.CANCELED)
                .build()
        assertThat(messageBatchCanceledResult).isNotNull
        assertThat(messageBatchCanceledResult.type())
            .isEqualTo(MessageBatchCanceledResult.Type.CANCELED)
    }
}
