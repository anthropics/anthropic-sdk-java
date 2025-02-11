// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageBatchErroredResultTest {

    @Test
    fun createMessageBatchErroredResult() {
        val messageBatchErroredResult =
            MessageBatchErroredResult.builder()
                .error(ErrorResponse.builder().invalidRequestErrorError("message").build())
                .build()
        assertThat(messageBatchErroredResult).isNotNull
        assertThat(messageBatchErroredResult.error())
            .isEqualTo(ErrorResponse.builder().invalidRequestErrorError("message").build())
    }
}
