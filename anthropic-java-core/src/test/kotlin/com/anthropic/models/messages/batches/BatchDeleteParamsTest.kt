// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages.batches

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BatchDeleteParamsTest {

    @Test
    fun create() {
        BatchDeleteParams.builder().messageBatchId("message_batch_id").build()
    }

    @Test
    fun getPathParam() {
        val params = BatchDeleteParams.builder().messageBatchId("message_batch_id").build()
        assertThat(params).isNotNull
        // path param "messageBatchId"
        assertThat(params.getPathParam(0)).isEqualTo("message_batch_id")
        // out-of-bound path param
        assertThat(params.getPathParam(1)).isEqualTo("")
    }
}
