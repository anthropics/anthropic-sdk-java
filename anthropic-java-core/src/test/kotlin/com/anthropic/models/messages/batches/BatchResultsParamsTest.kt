// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BatchResultsParamsTest {

    @Test
    fun create() {
        BatchResultsParams.builder().messageBatchId("message_batch_id").build()
    }

    @Test
    fun pathParams() {
        val params = BatchResultsParams.builder().messageBatchId("message_batch_id").build()

        assertThat(params._pathParam(0)).isEqualTo("message_batch_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
