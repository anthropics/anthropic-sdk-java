// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import com.anthropic.models.ErrorResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageBatchErroredResultTest {

    @Test
    fun create() {
        val messageBatchErroredResult =
            MessageBatchErroredResult.builder()
                .error(ErrorResponse.builder().invalidRequestErrorError("message").build())
                .build()

        assertThat(messageBatchErroredResult.error())
            .isEqualTo(ErrorResponse.builder().invalidRequestErrorError("message").build())
    }
}
